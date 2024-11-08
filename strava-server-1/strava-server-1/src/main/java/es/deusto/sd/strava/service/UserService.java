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
    private final Map<Long, User> users = new HashMap<>();
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
    	    token = new String(array, Charset.forName("UTF-8"));
    	}else {
    		byte[] array = new byte[32];
    	    new Random().nextBytes(array);
    	    token = new String(array, Charset.forName("UTF-8"));
    	}
    	User newUser = new User(idGenerator.incrementAndGet(),username, email,token, weight, height, maxheartRate, restHeartRate);
    	users.put(newUser.getId(), newUser);
    	return newUser;
    }
}
