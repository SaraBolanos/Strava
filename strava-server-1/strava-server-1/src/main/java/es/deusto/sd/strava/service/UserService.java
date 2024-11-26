package es.deusto.sd.strava.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.external.IFacebookAuthGateway;
import es.deusto.sd.strava.external.IGoogleAuthGateway;


@Service
public class UserService {
	// Auxiliary map to store the dishes as a repository.
    private final Map<Long, User> users = new HashMap<>();
    // AtomicLong to generate unique IDs for the dishes.
    private final AtomicLong idGenerator = new AtomicLong(0);
    
    private final IGoogleAuthGateway iGoogleAuthGateway;
    private final IFacebookAuthGateway iFacebookAuthGateway;
    
    public UserService(IGoogleAuthGateway iGoogleAuthGateway, IFacebookAuthGateway iFacebookAuthGateway) {
    	this.iGoogleAuthGateway = iGoogleAuthGateway;
    	this.iFacebookAuthGateway = iFacebookAuthGateway;
    }
    
    
    public void putUser(User newUser) {		//only for testing purpose
    	//users.put(newUser.getToken(), newUser);
	}

	public Optional<User> createUser(String accountType, String username, String email, String password, Optional<Float> weight, Optional<Float> height, Optional<Integer> maxheartRate, Optional<Integer> restHeartRate) {
		if(!verifyAccount(accountType,email,password)) {
    		return Optional.empty();
    	}
		
		User newUser = new User(username, email, weight, height, maxheartRate, restHeartRate);
    	users.put(idGenerator.incrementAndGet(), newUser);
    	return Optional.of(newUser);
    }
    
    public Optional<User> getUserByToken(String token) {
    	for(User user : users.values()) {
    		if(user.getToken().equals(token)) {
    			return Optional.of(user);
    		}
    	}
		return Optional.empty();
	}
    
    public Optional<User> getUserByEmail(String email) {
    	for(User user : users.values()) {
    		if(user.getEmail().equals(email)) {
    			return Optional.of(user);
    		}
    	}
		return Optional.empty();
	}
    
    public void logOut(String sessionToken) {
    	Optional<User> user = getUserByToken(sessionToken);
    	if(user.isPresent()) {
    		user.get().setToken(null);
    	}
    }
    
    public Optional<User> logIn(String accountType, String email, String password){
    	if(!verifyAccount(accountType,email,password)) {
    		return Optional.empty();
    	}
    	
    	Optional<User> user = getUserByEmail(email);
    	
    	if(user.isEmpty()) {
    		return Optional.empty();
    	}
    	
    	String uuid = UUID.randomUUID().toString();
    	user.get().setToken(uuid);
    	
    	return user;
    }
    
    private boolean verifyAccount(String accountType, String email, String password) {
    	if(accountType=="Google") {
    		return iGoogleAuthGateway.verifyGoogleAuth(email, password);
    	}else {
    		return iFacebookAuthGateway.verifyFacebookAuth(email, password);
    	}
    }
    
}
