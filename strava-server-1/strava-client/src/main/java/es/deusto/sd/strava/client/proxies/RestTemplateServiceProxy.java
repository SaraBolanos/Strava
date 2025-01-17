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

import es.deusto.sd.strava.client.data.Challenge;
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
        String url = apiBaseUrl + "/users/logout?sessiontoken={sessiontoken}";
        
        try {
            restTemplate.getForObject(url, Void.class, Map.of("sessiontoken", token));
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

	@Override
	public List<Challenge> getAcceptedChallenges(String userToken) {
		String url = apiBaseUrl + "/challenges/{userToken}/unfinished";
		try {
        	return Arrays.asList(restTemplate.getForObject(url, Challenge[].class, Map.of("userToken",userToken))) ;
            //return restTemplate.postForObject(url, credentials, String.class);
        } catch (HttpStatusCodeException e) {
            switch (e.getStatusCode().value()) {
                case 404 -> throw new RuntimeException("Fetch error: Not found.");
                default -> throw new RuntimeException("Fetch error: with status code: " + e.getStatusCode());
            }
        }
	}
	
	@Override
	public List<Challenge> getFinishedChallenges(String userToken) {
		String url = apiBaseUrl + "/challenges/{userToken}";
		try {
        	return Arrays.asList(restTemplate.getForObject(url, Challenge[].class, Map.of("userToken",userToken))) ;
            //return restTemplate.postForObject(url, credentials, String.class);
        } catch (HttpStatusCodeException e) {
            switch (e.getStatusCode().value()) {
                case 404 -> throw new RuntimeException("Fetch error: Not found.");
                default -> throw new RuntimeException("Fetch error: with status code: " + e.getStatusCode());
            }
        }
	}
	
	@Override
	public List<Challenge> getAllChallenges(String userToken) {
		String url = apiBaseUrl + "/challenges";
		try {
        	return Arrays.asList(restTemplate.getForObject(url, Challenge[].class)) ;
            //return restTemplate.postForObject(url, credentials, String.class);
        } catch (HttpStatusCodeException e) {
            switch (e.getStatusCode().value()) {
                case 404 -> throw new RuntimeException("Fetch error: Not found.");
                default -> throw new RuntimeException("Fetch error: with status code: " + e.getStatusCode());
            }
        }
	}

	@Override
	public Challenge acceptChallenge(int id, String usertoken) {
		String url = apiBaseUrl + "/challenges/{challengeId}?userToken={userToken}";
		try {
        	return restTemplate.postForObject(url, null, Challenge.class,Map.of("challengeId",id,"userToken",usertoken)) ;
            //return restTemplate.postForObject(url, credentials, String.class);
        } catch (HttpStatusCodeException e) {
            switch (e.getStatusCode().value()) {
                case 404 -> throw new RuntimeException("Fetch error: Not found.");
                default -> throw new RuntimeException("Fetch error: with status code: " + e.getStatusCode());
            }
        }
	}
	
	@Override
	public Challenge createChallenge(Challenge challenge, String userToken) {
		String url = apiBaseUrl + "/challenges?userToken={userToken}";
		try {
            return restTemplate.postForObject(url, challenge, Challenge.class, Map.of("userToken",userToken));
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

	@Override
	public Challenge getChallengeDetails(long challengeId) {
		String url = apiBaseUrl + "/challenge/{challengeId}";
		try {
			
        	return restTemplate.getForObject(url, Challenge.class, Map.of("challengeId",challengeId)) ;
        } catch (HttpStatusCodeException e) {
            switch (e.getStatusCode().value()) {
                case 404 -> throw new RuntimeException("Fetch error: Not found.");
                default -> throw new RuntimeException("Fetch error: with status code: " + e.getStatusCode());
            }
        }
	}

	@Override
	public float getPercentage(long challengeId, String userToken) {
		String url = apiBaseUrl + "/challenge/{challengeId}/percentage?token={userToken}";
		try {
			
        	return restTemplate.getForObject(url, float.class, Map.of("challengeId",challengeId,"userToken",userToken)) ;
        } catch (HttpStatusCodeException e) {
            switch (e.getStatusCode().value()) {
                case 404 -> throw new RuntimeException("Fetch error: Not found.");
                default -> throw new RuntimeException("Fetch error: with status code: " + e.getStatusCode());
            }
        }
	}

    
}