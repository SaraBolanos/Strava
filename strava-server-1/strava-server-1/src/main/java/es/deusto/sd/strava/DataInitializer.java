package es.deusto.sd.strava;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.deusto.sd.strava.dao.ChallengeRepository;
import es.deusto.sd.strava.dao.UserChallengeRepository;
import es.deusto.sd.strava.dao.UserRepository;
import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.enums.Sport;
import es.deusto.sd.strava.enums.TargetType;
import es.deusto.sd.strava.service.UserService;
import es.deusto.sd.strava.service.WorkoutService;
import es.deusto.sd.strava.service.ChallengeService;

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
	
    @Bean
    CommandLineRunner initData(ChallengeService challengeService, UserRepository userRepository, WorkoutService workoutService) {
        return args -> {
        	/*if(userRepository.count()==0) {
				return;
			}*/
            // Create some test users and log the actions
            logger.info("Initializing test users...");
                      
            User user1 = new User("user167", "e234j14751");
            user1.setToken("123");
            userRepository.save(user1);
   
            User user2 = new User();
            user2.setUsername("user2");
            user2.setToken("456");
            user2.setEmail("e2");
            userRepository.save(user2);


            // Add some example challenges and log the actions
            logger.info("Initializing test challenges...");

            challengeService.createChallenge("5k Running Challenge", "2024-01-01", "2024-10-12", 30.0f, TargetType.TIME, Sport.RUNNING, user1);
            logger.info("Challenge '5k Running Challenge' created for user1");

            challengeService.createChallenge("Cycling Endurance", "2024-03-01", "2025-03-15", 200.0f, TargetType.DISTANCE, Sport.CYCLING, user2);
            logger.info("Challenge 'Cycling Endurance' created for user2");
            
            

            challengeService.createChallenge("10k Running Challenge", "2024-02-01", "2025-02-28", 45.0f, TargetType.TIME, Sport.RUNNING, user1);
            logger.info("Challenge '10k Running Challenge' created for user1");

            challengeService.createChallenge("Cycling Sprint Challenge", "2024-04-01", "2025-04-30", 150.0f, TargetType.DISTANCE, Sport.CYCLING, user2);
            logger.info("Challenge 'Cycling Sprint Challenge' created for user2");

            challengeService.createChallenge("1 Hour Running Challenge", "2024-05-01", "2025-05-15", 60.0f, TargetType.TIME, Sport.RUNNING, user1);
            logger.info("Challenge '1 Hour Running Challenge' created for user1");
            

            // Simulate user accepting a challenge (for testing)
            challengeService.acceptChallenge(2, user1); // Challenge ID 2 for user1
            logger.info("User1 accepted challenge with ID 2");

            // Log challenge acceptance for testing
            logger.info("Challenges for user1 after acceptance:");
            challengeService.getAcceptedChallenges(user1).forEach(challenge -> {
                logger.info("Accepted Challenge: " + challenge.getName());
            });
            


			logger.info("Challenges saved:");
			
			
			
			
			challengeService.getAllChallengesTEST().forEach(challenge2 -> {
	            logger.info(challenge2.getName());
	        });
			
			//Create some workouts and log the actions
			logger.info("Initializing test challenges...");
			
			
			workoutService.createWorkout(1, "Easy Run", Sport.RUNNING, 10.0f, Date.valueOf("2024-12-01"), LocalTime.of(6, 30), 1.0f, user2);
            logger.info("'Easy Run' logged for user2");

			workoutService.createWorkout(2, "Long Run", Sport.RUNNING, 20.0f, Date.valueOf("2024-12-02"), LocalTime.of(6, 30), 2.0f, user1);
			 logger.info("'Long Run' logged for user1");
			 
			 logger.info("Runs saved:");
			
		};
	}

		
    	
}