package es.deusto.sd.facebook.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.deusto.sd.facebook.entity.User;


@Service
public class UserService {
	// Auxiliary map to store the dishes as a repository.
    private final Map<Long, User> users = new HashMap<>();
    // AtomicLong to generate unique IDs for the dishes.
    private final AtomicLong idGenerator = new AtomicLong(0);
    
    public UserService() {  
    	
    }

	public boolean login(String email, String password) {
		Optional<User> user = getUserByEmail(email);
		if(!user.isEmpty()) {
			if(user.get().getPassword().equals(password)) {
				return true;
			}
		}
    	return false;
    }
    
    public Optional<User> getUserByEmail(String email) {
    	for(User user : users.values()) {
    		if(user.getEmail().equals(email)) {
    			return Optional.of(user);
    		}
    	}
    	return Optional.empty();
	}
    
    public boolean checkIfUserExists(String token) {
    	return users.containsKey(token);
    }
}
