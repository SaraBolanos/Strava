package es.deusto.sd.googleAuth.service;

import es.deusto.sd.googleAuth.dao.UserRepository;

public class AuthService {
	private final UserRepository userRepository;
	
	public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
