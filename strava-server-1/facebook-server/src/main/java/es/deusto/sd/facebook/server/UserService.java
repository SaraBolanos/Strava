package es.deusto.sd.facebook.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;


@Service
public class UserService {
	// Auxiliary map to store the dishes as a repository.
    private final Map<Long, User> users = new HashMap<>();
    // AtomicLong to generate unique IDs for the dishes.
    private final AtomicLong idGenerator = new AtomicLong(0);
    
    public UserService() {  
    	users.put(idGenerator.incrementAndGet(), new User("m.sannadi@brkcf.com", "brknuncaserinde"));
    	users.put(idGenerator.incrementAndGet(), new User("amongus@vent.sus", "imposter"));
    	users.put(idGenerator.incrementAndGet(), new User("cirno@fumo.info", "frozenfrogs"));
    	users.put(idGenerator.incrementAndGet(), new User("limmy@bbc.com", "ripBenryHarvey"));
    	users.put(idGenerator.incrementAndGet(), new User("hideo.kojima@hideokojimaproductions.com", "kojimahideo"));
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
}
