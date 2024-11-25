package es.deusto.sd.googleAuth.facade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.deusto.sd.googleAuth.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	private AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@GetMapping("/verify")
	public ResponseEntity<Boolean> verify(@RequestParam("email") String email, @RequestParam("password") String password){
		if(authService.verify(email, password)) {
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.FORBIDDEN);
	}
	
}
