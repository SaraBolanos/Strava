package es.deusto.sd.strava.facade;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.deusto.sd.strava.entity.Session;
import es.deusto.sd.strava.service.SessionService;
import es.deusto.sd.strava.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user/session")
@Tag(name = "User Session", description = "Operations related to users")
public class SessionController {
	
	private final SessionService sessionService;
	
	public SessionController(SessionService sessionService) {
		this.sessionService = sessionService;
	}
	
	@PostMapping
	public ResponseEntity<Session> logIn(@RequestParam("accountType") String accountType,
										 @RequestParam("email") String email,
										 @RequestParam("password") String password,
										 @RequestParam("tempkey") String tempkey){
		if(!accountType.equals("Google") && !accountType.equals("Facebook")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Optional<Session> newSession = sessionService.logIn(accountType, email, password, tempkey);
		if(newSession.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<>(newSession.get() ,HttpStatus.CREATED);
		}

	}
	
	@DeleteMapping
	public ResponseEntity<Session> logOut(@CookieValue("sessionToken") String sessionToken){
		sessionService.logOut(sessionToken);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
