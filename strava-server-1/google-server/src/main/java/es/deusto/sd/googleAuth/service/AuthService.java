package es.deusto.sd.googleAuth.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import es.deusto.sd.googleAuth.dao.UserRepository;
import es.deusto.sd.googleAuth.entity.User;

@Service
public class AuthService {
	private final UserRepository userRepository;
	
	public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	public boolean verify(String email, String password){
		Optional<User> user = userRepository.findByEmail(email);
		
		if (user.isPresent() && user.get().getPassword().equals(password)) {
			return true;
		}
		return false;
	}
}
