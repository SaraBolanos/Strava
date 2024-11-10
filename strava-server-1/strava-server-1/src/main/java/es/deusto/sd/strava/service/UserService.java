package es.deusto.sd.strava.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.deusto.sd.strava.entity.User;


@Service
public class UserService {
	// Auxiliary map to store the dishes as a repository.
    private final Map<String, User> users = new HashMap<>();
    // AtomicLong to generate unique IDs for the dishes.
    private final AtomicLong idGenerator = new AtomicLong(0);
    
    public UserService() {  
    	
    }
    
    public User createUser(String accountType, String username, String email, String password, Optional<Float> weight, Optional<Float> height, Optional<Integer> maxheartRate, Optional<Integer> restHeartRate) {
    	
    	String token;
    	
    	if(accountType.equals("Google")) {
    		//here would go the Google logic
    	    token = "Google" + idGenerator.incrementAndGet();
    	}else {
    		//here Would Go the Facebook logic
    	    token = "Facebook" + idGenerator.incrementAndGet();
    	}
    	User newUser = new User(username, email,token, weight, height, maxheartRate, restHeartRate);
    	users.put(token, newUser);
    	return newUser;
    }
    
    public Optional<User> getUserByToken(String token) {
    	User user = users.get(token);
		if(user!=null) {
			return Optional.of(user);
		}else {
			return Optional.empty();
		}
	}
    
    public boolean checkIfUserExists(String token) {
    	return users.containsKey(token);
    }
}
