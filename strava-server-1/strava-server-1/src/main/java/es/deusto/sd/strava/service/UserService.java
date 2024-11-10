package es.deusto.sd.strava.service;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    	
    	if(accountType == "Google") {
    		//here would go the Google logic
    		byte[] array = new byte[32];
    	    new Random().nextBytes(array);
    	    token = "Google" + idGenerator.incrementAndGet();
    	}else {
    		byte[] array = new byte[32];
    	    new Random().nextBytes(array);
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
