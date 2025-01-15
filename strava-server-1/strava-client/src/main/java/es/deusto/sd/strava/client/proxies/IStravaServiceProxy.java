package es.deusto.sd.strava.client.proxies;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import es.deusto.sd.strava.client.data.Article;
import es.deusto.sd.strava.client.data.Category;
import es.deusto.sd.strava.client.data.Credentials;
import es.deusto.sd.strava.client.data.SignupRequest;

public interface IStravaServiceProxy {
	// Method for user login
	String login(Credentials credentials);
	
	String signup(SignupRequest signupRequest);

	// Method for user logout
	void logout(String token);
	
}
