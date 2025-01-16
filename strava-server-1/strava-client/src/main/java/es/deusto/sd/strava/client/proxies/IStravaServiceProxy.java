package es.deusto.sd.strava.client.proxies;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import es.deusto.sd.strava.client.data.Challenge;
import es.deusto.sd.strava.client.data.Credentials;
import es.deusto.sd.strava.client.data.SignupRequest;
import es.deusto.sd.strava.client.data.User;
import es.deusto.sd.strava.client.data.Workout;

public interface IStravaServiceProxy {
	// Method for user login
	User login(Credentials credentials);
	
	User signup(SignupRequest signupRequest);

	// Method for user logout
	void logout(String token);
	
    List<Workout> getUserWorkout(String userToken);
    
    Workout createWorkout(Workout workout, String userToken);
    
    List<Challenge> getAcceptedChallenges(String userToken);
}
