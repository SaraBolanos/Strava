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
import es.deusto.sd.strava.dao.WorkoutRepository;
import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.entity.Workout;
import es.deusto.sd.strava.enums.Sport;
import es.deusto.sd.strava.enums.TargetType;
import es.deusto.sd.strava.service.UserService;
import es.deusto.sd.strava.service.WorkoutService;
import es.deusto.sd.strava.service.ChallengeService;

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
	
    @Bean
    CommandLineRunner initData(ChallengeService challengeService,ChallengeRepository challengeRepository, UserRepository userRepository, WorkoutRepository workoutRepository) {
        return args -> {
        	User user1;
        	User user2;
        	if(userRepository.count()==0) {
        		// Create some test users and log the actions
        		logger.info("Initializing test users...");
                
                user1 = new User("PeterClementAdvance", "peter.clement@advance.uk");
                userRepository.save(user1);
       
                user2 = new User("MaroanSannadi", "m.sannadi@brkcf.com");
                userRepository.save(user2);
                logger.info("Done");
			}else {
				logger.info("Users Exist");
				user1 = userRepository.findByEmail("peter.clement@advance.uk");
				user2 = userRepository.findByEmail("m.sannadi@brkcf.com");
			}
        	if(workoutRepository.count()==0) {
        		logger.info("Initializing test workouts...");
        		Workout workout1 = new Workout(1, "Long Run", Sport.RUNNING, 20.0f, Date.valueOf("2024-12-02"), LocalTime.of(6, 30), 2.0f, user1);
        		workoutRepository.save(workout1);
        		Workout workout2 = new Workout(2, "Longer Run", Sport.RUNNING, 25.0f, Date.valueOf("2024-12-02"), LocalTime.of(6, 30), 2.0f, user1);
        		workoutRepository.save(workout2);
        		Workout workout3 = new Workout(3, "Mountain Route", Sport.CYCLING, 30.0f, Date.valueOf("2024-12-22"), LocalTime.of(8, 45), 2.0f, user2);
        		workoutRepository.save(workout3);
        		logger.info("Done");
        	}else {
        		logger.info("Workouts Exist");
        	}
            if(challengeRepository.count()==0) {
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
            }else {
            	logger.info("Challenges Exist");
            }

            // Add some example challenges and log the actions
            

            
            


			
			
			
			
			
			/*challengeService.getAllChallengesTEST().forEach(challenge2 -> {
	            logger.info(challenge2.getName());
	        });*/
			
			
			
		};
	}

		
    	
}