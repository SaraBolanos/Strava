package es.deusto.sd.facebook.facade;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import es.deusto.sd.facebook.entity.User;
import es.deusto.sd.facebook.service.UserService;

@RestController
@RequestMapping("/user")
@Tag(name = "User Controller", description = "Operations related to users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//Create user
	@GetMapping
	public ResponseEntity<User> logIn(@RequestParam String email,
									  @RequestParam String password){
		User newUser = userService.createUser(accountType, username, email, password, weight, height, maxheartRate, restHeartRate);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		
	}
}
