package es.deusto.sd.strava.client.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.deusto.sd.strava.client.data.Article;
import es.deusto.sd.strava.client.data.Category;
import es.deusto.sd.strava.client.data.Credentials;
import es.deusto.sd.strava.client.proxies.IStravaServiceProxy;
import jakarta.servlet.http.HttpServletRequest;
import es.deusto.sd.strava.client.data.SignupRequest;
import es.deusto.sd.strava.client.data.User;

@Controller
public class WebClientController {

	@Autowired
	private IStravaServiceProxy stravaServiceProxy;

	private User user; // Stores the session token

	// Add current URL and token to all views
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		String currentUrl = ServletUriComponentsBuilder.fromRequestUri(request).toUriString();
		model.addAttribute("currentUrl", currentUrl); // Makes current URL available in all templates
		model.addAttribute("user", user); // Makes token available in all templates
	}

	@GetMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@GetMapping("/signup")
	public String registerpage(Model model) {
		return "signup";
	}
	
	@GetMapping("/sessions")
	public String sessionspage(Model model) {
		return "sessions";
	}
	
	@PostMapping("/signup")
	public String performRegister(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("method")String method,@RequestParam("username") String username, @RequestParam("weight") Optional<Float> weight,
			@RequestParam("height")Optional<Float> height,@RequestParam("maxheartrate")Optional<Integer> maxheartrate,
			@RequestParam("restheartrate")Optional<Integer> restheartrate,Model model) {
		SignupRequest signupRequest = new SignupRequest(email, password, method, username, weight, height, maxheartrate, restheartrate);
		try {
			user = stravaServiceProxy.signup(signupRequest);
			
			// Redirect to the original page or root if redirectUrl is null
			return "redirect:sessions";
		} catch (RuntimeException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "signup"; // Return to login page with error message
		}
	}

	@PostMapping("/login")
	public String performLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			@RequestParam("method")String method, RedirectAttributes redirectAttrs) {
		Credentials credentials = new Credentials(email, password, method);

		try {
			user = stravaServiceProxy.login(credentials);

			// Redirect to the original page or root if redirectUrl is null
			return "redirect:sessions";
		} catch (RuntimeException e) {
			redirectAttrs.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/"; // Return to login page with error message
		}
	}

	@GetMapping("/logout")
	public String performLogout(@RequestParam(value = "redirectUrl", defaultValue = "/") String redirectUrl,
			Model model) {
		try {
			stravaServiceProxy.logout(user.getToken());
			user = null; // Clear the token after logout
			model.addAttribute("successMessage", "Logout successful.");
		} catch (RuntimeException e) {
			model.addAttribute("errorMessage", "Logout failed: " + e.getMessage());
		}

		// Redirect to the specified URL after logout
		return "redirect:" + redirectUrl;
	}

}