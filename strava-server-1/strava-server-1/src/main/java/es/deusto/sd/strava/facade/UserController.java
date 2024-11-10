package es.deusto.sd.strava.facade;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.service.UserService;

@RestController
@RequestMapping("/user")
@Tag(name = "User Controller", description = "Operations related to users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//Create user
	@PostMapping
	public ResponseEntity<User> createUser(@RequestParam("username") String username,
										   @RequestParam("accountType") String accountType,
										   @RequestParam("email") String email,
										   @RequestParam("password") String password,
										   @RequestParam("weight") Optional<Float> weight,
										   @RequestParam("height") Optional<Float> height,
										   @RequestParam("maxheartRate") Optional<Integer> maxheartRate,
										   @RequestParam("restHeartRate") Optional<Integer> restHeartRate){
		if(!accountType.equals("Google") && !accountType.equals("Facebook")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User newUser = userService.createUser(accountType, username, email, password, weight, height, maxheartRate, restHeartRate);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		
	}
}
