package es.deusto.sd.strava.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import es.deusto.sd.strava.entity.Session;

@Service
public class SessionService {

	private final UserService userService;
	
	public SessionService(UserService userService) {
		this.userService = userService;
	}
	
	private static String generateSessionToken() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
	
	public Optional<Session> logIn(@RequestParam("accountType") String accountType,
			 	  @RequestParam("email") String email,
			 	  @RequestParam("password") String password,
			 	  @RequestParam("tempkey") String tempkey) {
		/* Here would go the real Google/Facebook implementation, but because we don't have one
		 * we temporary esu the parameter tempKey as a simulated response from they servers. This will be removed later */
		if(userService.checkIfUserExists(tempkey)) {
			String newSessionToken = generateSessionToken();
			Session newSession = new Session(newSessionToken, tempkey);
			return Optional.of(newSession);
			
		}else {
			return Optional.empty();
		}
		
	}
	
}
