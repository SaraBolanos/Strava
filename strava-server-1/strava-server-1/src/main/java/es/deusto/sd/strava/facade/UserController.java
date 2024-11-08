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
	@Operation(
	        summary = "Login to the system",
	        description = "Allows a user to login by providing email and password. Returns a token if successful.",
	        responses = {
	            @ApiResponse(responseCode = "200", description = "OK: Login successful, returns a token"),
	            @ApiResponse(responseCode = "401", description = "Unauthorized: Invalid credentials, login failed"),
	        }
	    )
	@PostMapping
	public ResponseEntity<User> createUser(@RequestParam("username") String accountType,
										   @RequestParam("acountType") String username,
										   @RequestParam("email") String email,
										   @RequestParam("password") String password,
										   @RequestParam("weight") Optional<Float> weight,
										   @RequestParam("height") Optional<Float> height,
										   @RequestParam("maxheartRate") Optional<Integer> maxheartRate,
										   @RequestParam("restHeartRate") Optional<Integer> restHeartRate){
		if(accountType != "Google" && accountType != "Facebook") {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		User newUser = userService.createUser(accountType, username, email, password, weight, height, maxheartRate, restHeartRate);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		
	}
}
