/**
 * This code is based on solutions provided by ChatGPT 4o and 
 * adapted using GitHub Copilot. It has been thoroughly reviewed 
 * and validated to ensure correctness and that it is free of errors.
 */
package es.deusto.sd.strava;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.enums.Sport;
import es.deusto.sd.strava.enums.TargetType;
import es.deusto.sd.strava.service.StravaService;
import es.deusto.sd.strava.service.UserService;
import es.deusto.sd.strava.service.AuthService;
import es.deusto.sd.strava.service.ChallengeService;

@Configuration
public class DataInitializer {

	private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
	
	
	@Bean
    CommandLineRunner initData(ChallengeService challengeService, UserService userService) {
		return args -> {			
			// Create some users
			
			User user1 =new User();
			user1.setUsername("kevin");
			user1.setToken("123");
			userService.putUser(user1);
			
			User user2 =new User();
			userService.putUser(user2);
	    
	    // Add some example challenges
			challengeService.createChallenge("5k Running Challenge", "2024-01-01", "2024-10-12", 30.0f, TargetType.TIME, Sport.RUNNING, user1);
			challengeService.createChallenge("Cycling Endurance", "2024-03-01", "2025-03-15", 200.0f, TargetType.DISTANCE, Sport.CYCLING, user2);
			challengeService.createChallenge("10k Running Challenge", "2024-02-01", "2025-02-28", 45.0f, TargetType.TIME, Sport.RUNNING, user1);
			challengeService.createChallenge("Cycling Sprint Challenge", "2024-04-01", "2025-04-30", 150.0f, TargetType.DISTANCE, Sport.CYCLING, user2);
			challengeService.createChallenge("1 Hour Running Challenge", "2024-05-01", "2025-05-15", 60, TargetType.TIME, Sport.RUNNING, user1);
			
			
			challengeService.acceptChallenge(2, user1);
			
			
			logger.info("Challenges saved:");
			
			challengeService.getAllChallengesTest().forEach(challenge2 -> {
	            logger.info(challenge2.getName());
	        });
			
			
		};
	}
			
    	
}