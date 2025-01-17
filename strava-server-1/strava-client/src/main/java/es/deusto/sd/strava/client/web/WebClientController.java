package es.deusto.sd.strava.client.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.sql.Date;
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

import es.deusto.sd.strava.client.data.Challenge;
import es.deusto.sd.strava.client.data.Credentials;
import es.deusto.sd.strava.client.proxies.IStravaServiceProxy;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import es.deusto.sd.strava.client.data.SignupRequest;
import es.deusto.sd.strava.client.data.User;
import es.deusto.sd.strava.client.data.Workout;
import es.deusto.sd.strava.client.enums.Sport;
import es.deusto.sd.strava.client.enums.TargetType;

@Controller
public class WebClientController {

	@Autowired
	private IStravaServiceProxy stravaServiceProxy;

	private User user; // Stores the session token
	
	private List<Workout> workouts;
	private List<Challenge> challenges;
	private List<Challenge> allchallenges;
	// Add current URL and token to all views
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {
		String currentUrl = ServletUriComponentsBuilder.fromRequestUri(request).toUriString();
		model.addAttribute("currentUrl", currentUrl); // Makes current URL available in all templates
		model.addAttribute("user", user); // Makes token available in all templates
		model.addAttribute("workouts", workouts);
		model.addAttribute("challenges", challenges);
		model.addAttribute("allchallenges", allchallenges);
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
		
		try {
			workouts = stravaServiceProxy.getUserWorkout(user.getToken());
			model.addAttribute("workouts", workouts);
		} catch (RuntimeException e) {
			model.addAttribute("errorMessage","No training sessions ");
			model.addAttribute("workouts", null);
		}
		
		return "sessions";
	}
	
	@GetMapping("/challenges")
	public String challengespage(Model model) {
		
		try {
			challenges = stravaServiceProxy.getAcceptedChallenges(user.getToken());
			allchallenges = stravaServiceProxy.getAllChallenges(user.getToken());
			model.addAttribute("challenges", challenges);
			model.addAttribute("allchallenges", allchallenges);
		} catch (RuntimeException e) {
			model.addAttribute("challenges", null);
		}
		
		return "challenges";
	}
	
	@GetMapping("/challenge/{id}")
	public String challengeDetailPage(@PathVariable("id") long id, Model model) {
		try {
			Challenge challenge;
			
			challenge = stravaServiceProxy.getChallengeDetails(id);
			
			model.addAttribute("challenge", challenge);

		} catch (RuntimeException e) {
		    model.addAttribute("errorMessage", "There was an error when loading the challenge.");
			model.addAttribute("challenge", null);
		}

		return "challenge";
	}
	
	@PostMapping("/sessions")
	public String performCreateSession(@RequestParam("title") String title, @RequestParam("sport") String sport,
			@RequestParam("distance")float distance,@RequestParam("startDate") String startDate, @RequestParam("startTime") String startTime,
			@RequestParam("duration")float duration, Model model,RedirectAttributes redirectAttrs) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Workout workout = new Workout(title, Sport.valueOf(sport.toUpperCase()), distance, new java.sql.Date(formatter.parse(startDate).getTime()) , LocalTime.parse(startTime), duration);
		try {
			stravaServiceProxy.createWorkout(workout, user.getToken());
			redirectAttrs.addFlashAttribute("okMessage", "Session created");
			// Redirect to the original page or root if redirectUrl is null
			return "redirect:sessions";
		} catch (RuntimeException e) {
			redirectAttrs.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:sessions"; // Return to login page with error message
		}
		
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
	public String performLogout(Model model) {
		try {
			stravaServiceProxy.logout(user.getToken());
			user = null; // Clear the token after logout
		} catch (RuntimeException e) {
			model.addAttribute("errorMessage", "Logout failed: " + e.getMessage());
		}

		// Redirect to the specified URL after logout
		return "redirect:/";
	}
	
	@GetMapping("/challenge/{id}/accept")
	public String performAcceptChallenge(@PathVariable("id") int id, Model model,RedirectAttributes redirectAttrs) {
		try {
			stravaServiceProxy.acceptChallenge(id, user.getToken());
			redirectAttrs.addFlashAttribute("okMessage", "ChallengeAccepted");
		} catch (RuntimeException e) {
			redirectAttrs.addFlashAttribute("errorMessage", "Error when accepting challenge");
		}

		// Redirect to the specified URL after logout
		return "redirect:/challenges";
	}
	

	
	@PostMapping("/challenges")
	public String performCreateChallenge(@RequestParam("name") String name, @RequestParam("sport") String sport,
			@RequestParam("targettype")String targettype,@RequestParam("target") float target, @RequestParam("startDate") String startDate,
			@RequestParam("endDate")String endDate, Model model,RedirectAttributes redirectAttrs) throws ParseException {
		Challenge challenge = new Challenge(0, name, startDate, endDate, target,TargetType.valueOf(targettype.toUpperCase()) ,Sport.valueOf(sport.toUpperCase()), user.getUsername());
		try {
			stravaServiceProxy.createChallenge(challenge, user.getToken());
			redirectAttrs.addFlashAttribute("okMessage", "Challenge created");
			// Redirect to the original page or root if redirectUrl is null
			return "redirect:challenges";
		} catch (RuntimeException e) {
			redirectAttrs.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:challenges"; // Return to login page with error message
		}
		
	}

}