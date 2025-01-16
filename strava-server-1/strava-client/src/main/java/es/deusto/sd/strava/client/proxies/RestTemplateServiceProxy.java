/**
 * This code is based on solutions provided by Claude Sonnet 3.5 and 
 * adapted using GitHub Copilot. It has been thoroughly reviewed 
 * and validated to ensure correctness and that it is free of errors.
 */
package es.deusto.sd.strava.client.proxies;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import es.deusto.sd.strava.client.data.Article;
import es.deusto.sd.strava.client.data.Category;
import es.deusto.sd.strava.client.data.Credentials;
import es.deusto.sd.strava.client.data.SignupRequest;
import es.deusto.sd.strava.client.data.User;
import es.deusto.sd.strava.client.data.Workout;

@Service
public class RestTemplateServiceProxy implements IStravaServiceProxy{

    private final RestTemplate restTemplate;

    @Value("${api.base.url}")
    private String apiBaseUrl;

    public RestTemplateServiceProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public User login(Credentials credentials) {
        String url = apiBaseUrl + "/users/login?email={email}&password={password}&accountType={accountType}";
        
        try {
        	return restTemplate.getForObject(url, User.class, Map.of("email",credentials.email(),"password",credentials.password(),"accountType",credentials.accountType()));
            //return restTemplate.postForObject(url, credentials, String.class);
        } catch (HttpStatusCodeException e) {
            switch (e.getStatusCode().value()) {
            	case 400 -> throw new RuntimeException("Login failed: Missing parameters.");
                case 403 -> throw new RuntimeException("Login failed: Invalid credentials.");
                default -> throw new RuntimeException("Login failed with status code: " + e.getStatusCode());
            }
        }
    }
    
    @Override
    public User signup(SignupRequest signupRequest) {
    	String url = apiBaseUrl + "/users";
        
        try {
            return restTemplate.postForObject(url, signupRequest, User.class);
        }catch (HttpStatusCodeException e) {
	            switch (e.getStatusCode().value()) {
	        	case 400 -> throw new RuntimeException("Sign up failed: Missing parameters.");
	            case 403 -> throw new RuntimeException("Sign up failed: Invalid credentials.");
	            case 409 -> throw new RuntimeException("Sign up failed: Email already registered.");
	            default -> throw new RuntimeException("Sign up failed with status code: " + e.getStatusCode());
	        }
	    }
    }
    
    @Override    
    public void logout(String token) {
        String url = apiBaseUrl + "/auth/logout";
        
        try {
            restTemplate.postForObject(url, token, Void.class);
        } catch (HttpStatusCodeException e) {
            switch (e.getStatusCode().value()) {
                case 401 -> throw new RuntimeException("Logout failed: Invalid token.");
                default -> throw new RuntimeException("Logout failed: " + e.getStatusText());
            }
        }
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Workout> getUserWorkout(String userToken) {
		String url = apiBaseUrl + "/workouts?userToken={userToken}";
		try {
        	return Arrays.asList(restTemplate.getForObject(url, Workout[].class, Map.of("userToken",userToken))) ;
            //return restTemplate.postForObject(url, credentials, String.class);
        } catch (HttpStatusCodeException e) {
            switch (e.getStatusCode().value()) {
                case 404 -> throw new RuntimeException("Fetch error: Not found.");
                default -> throw new RuntimeException("Fetch error: with status code: " + e.getStatusCode());
            }
        }
	}

	@Override
	public Workout createWorkout(Workout workout, String userToken) {
		String url = apiBaseUrl + "/workouts?userToken={userToken}";
		try {
            return restTemplate.postForObject(url, workout, Workout.class, Map.of("userToken",userToken));
        }catch (HttpStatusCodeException e) {
	            switch (e.getStatusCode().value()) {
	            case 400 -> throw new RuntimeException("Invalid date format");
	            case 401 -> throw new RuntimeException("You can't be here.");
	            case 403 -> throw new RuntimeException("Sign up failed: Invalid credentials.");
	            case 409 -> throw new RuntimeException("Sign up failed: Email already registered.");
	            default -> throw new RuntimeException("Sign up failed with status code: " + e.getStatusCode());
	        }
	    }
	}

    
}