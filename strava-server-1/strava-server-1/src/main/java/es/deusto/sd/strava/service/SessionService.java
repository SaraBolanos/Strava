package es.deusto.sd.strava.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import es.deusto.sd.strava.entity.Session;
import es.deusto.sd.strava.entity.User;

@Service
public class SessionService {

	private final Map<String,Session> sessions = new HashMap<>();
	
	private final UserService userService;
	
	public SessionService(UserService userService) {
		this.userService = userService;
	}
	
	private static String generateSessionToken() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
	
	public Optional<Session> logIn(String accountType,
			 	  String email,
			 	  String password,
			 	  String tempkey) {
		/* Here would go the real Google/Facebook implementation, but because we don't have one
		 * we temporary use the parameter tempKey as a simulated response from they servers. This will be removed later */
		if(userService.checkIfUserExists(tempkey)) {
			String newSessionToken = generateSessionToken();
			Session newSession = new Session(newSessionToken, tempkey);
			sessions.put(newSessionToken, newSession);
			return Optional.of(newSession);
		}else {
			return Optional.empty();
		}
		
	}
	
	public void logOut(String sessionToken) {
		if(sessions.containsKey(sessionToken)) {
			sessions.remove(sessionToken);
		}
	}
	
	public Optional<User> getUserBySessionToken(String sessionToken){
		if(!sessions.containsKey(sessionToken)) {
			return Optional.empty();
		}
		return userService.getUserByToken(sessions.get(sessionToken).getUserToken());
	}
	
}
