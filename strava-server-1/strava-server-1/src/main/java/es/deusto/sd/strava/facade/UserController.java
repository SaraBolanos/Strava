package es.deusto.sd.strava.facade;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import es.deusto.sd.strava.dto.SignupRequestDTO;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.service.UserService;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "Operations related to users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//Create user
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody SignupRequestDTO signupRequestDTO){
		if(!signupRequestDTO.getMethod().equals("Google") && !signupRequestDTO.getMethod().equals("Facebook")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		//Check if user already exists
		if(userService.getUserByEmail(signupRequestDTO.getEmail()).isPresent()) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		Optional<User> newUser = userService.createUser(signupRequestDTO.getMethod(), 
														signupRequestDTO.getUsername(), 
														signupRequestDTO.getEmail(), 
														signupRequestDTO.getPassword(),
														signupRequestDTO.getWeight(), 
														signupRequestDTO.getHeight(), 
														signupRequestDTO.getMaxHeartRate(), 
														signupRequestDTO.getRestHeartRate());
		
		if(newUser.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(newUser.get(), HttpStatus.CREATED);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<User> logout(@RequestParam String sessiontoken){
		userService.logOut(sessiontoken);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@GetMapping("/login")
	public ResponseEntity<User> login(@RequestParam String email,
								@RequestParam String password,
								@RequestParam String accountType){
		if(!accountType.equals("Google") && !accountType.equals("Facebook")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<User> loggedUser = userService.logIn(accountType, email, password);
		
		if(loggedUser.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		return new ResponseEntity<>(loggedUser.get(), HttpStatus.CREATED);
		
	}
}
