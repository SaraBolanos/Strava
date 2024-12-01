package es.deusto.sd.strava.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import es.deusto.sd.strava.dao.UserRepository;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.external.AuthGatewayFactory;
import es.deusto.sd.strava.external.IAuthGateway;

@Service
public class UserService {
	private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
    	this.userRepository = userRepository;
    }
    
    
    public void putUser(User newUser) {		//only for testing purpose
    	//users.put(newUser.getToken(), newUser);
	}

	public Optional<User> createUser(String accountType, String username, String email, String password, Optional<Float> weight, Optional<Float> height, Optional<Integer> maxheartRate, Optional<Integer> restHeartRate) {
		if(!verifyAccount(accountType,email,password)) {
    		return Optional.empty();
    	}
		
		User newUser = new User(username, email, weight, height, maxheartRate, restHeartRate);
		userRepository.save(newUser);
    	return Optional.of(newUser);
    }
    
    public Optional<User> getUserByToken(String token) {
    	User user = userRepository.findByToken(token);
		return Optional.ofNullable(user);
	}
    
    public Optional<User> getUserByEmail(String email) {
    	User user = userRepository.findByEmail(email);
    	return Optional.ofNullable(user);
	}
    
    public void logOut(String sessionToken) {
    	Optional<User> user = getUserByToken(sessionToken);
    	if(user.isPresent()) {
    		user.get().setToken(null);
    		userRepository.save(user.get());
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
    	userRepository.save(user.get());
    	
    	return user;
    }
    
    private boolean verifyAccount(String accountType, String email, String password) {
    	IAuthGateway gateway = AuthGatewayFactory.createAuthGateway(accountType);
    	return gateway.verifyAuth(email, password);
    }
    
}
