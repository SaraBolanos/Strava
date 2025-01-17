package es.deusto.sd.strava.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import es.deusto.sd.strava.dao.ChallengeRepository;
import es.deusto.sd.strava.dao.UserChallengeRepository;
import es.deusto.sd.strava.dao.UserRepository;
import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.entity.UserChallenge;
import es.deusto.sd.strava.entity.Workout;
import es.deusto.sd.strava.enums.Sport;
import es.deusto.sd.strava.enums.TargetType;

@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final UserChallengeRepository userChallengeRepository;
    private final UserRepository userRepository;

    public ChallengeService(ChallengeRepository challengeRepository, UserChallengeRepository userChallengeRepository, UserRepository userRepository) {
        this.challengeRepository = challengeRepository;
        this.userChallengeRepository = userChallengeRepository;
        this.userRepository = userRepository;
    }

    // Create a new challenge
    public Challenge createChallenge(String name, String startDate, String endDate, float target, TargetType targetType, Sport sport, User user) {
        Challenge challenge = new Challenge(name, startDate, endDate, target, targetType, sport, user);
        return challengeRepository.save(challenge);  // Save challenge to the database
    }
    public List<Challenge> getAllChallengesTEST() {
    	return challengeRepository.findAll();
    }

    // Get all challenges
    public List<Challenge> getAllChallenges(String dateString, Sport sport) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");      

        if (sport != null) {
            if (dateString != null) { // Filter by both sport and date
            	
            	return challengeRepository.findActiveChallengesBySportAndDate(sport, LocalDate.parse(dateString, formatter));
            
               
            } else { // Filter only by sport
                return challengeRepository.findActiveChallengesBySport(sport.toString());
            }
        }
        if (dateString != null) { // Filter only by date
        	return challengeRepository.findActiveChallengesByDate(LocalDate.parse(dateString, formatter));
        }

        return challengeRepository.findActiveChallenges();
    }
    
    public Challenge getChallenge(long challengeId) {
      
        return challengeRepository.findChallenge(challengeId);
    }
    
    public UserChallenge getUserChallenge(User user, long challengeId) {
        
        return userChallengeRepository.findByUserAndChallengeId(user, challengeId);
    }
    
    
    

    // Get accepted challenges for a user
    public List<Challenge> getAcceptedChallenges(User user) {
        return userChallengeRepository.findAcceptedUserChallenges(user.getId());
    }

    // Get unfinished challenges for a user
    public List<Challenge> getUnfinishedChallenges(User user) {
    	return userChallengeRepository.findUnfinishedChallengesForUser(user.getId());
    }
    
    public List<Challenge> getFinishedChallenges(User user) {
    	return userChallengeRepository.findFinishedChallengesForUser(user.getId());
    }

    // Accept a challenge
    public Challenge acceptChallenge(long id, User user) {
        Optional<Challenge> challengeToAccept = challengeRepository.findById(id);
        try{
        	if(getUserChallenge(user, id)!=null) {return null;}
        	}catch(Exception e) {
        		return null;
        	}
        challengeToAccept.ifPresent(challenge -> {
        	UserChallenge userChallenge = new UserChallenge(challenge, user);
            userChallengeRepository.save(userChallenge);  // Save the association to the database
        });

        return challengeToAccept.orElse(null);
    }

    // Get percentage of achievement for a challenge
    public float getPercentageOfAchievement(long id, User user) {
    	Float percentage = userChallengeRepository.getPercentageOfAchievement(user.getId(), id);
    	
        // If the result is null, return 0f
        return (percentage != null) ? percentage : 0f;
    }
    
    public float getPercentageOfAchievement2(List<Workout> workouts, float target, TargetType targetType, long userChallengeId) {
    	if(target == 0) {
    		return 0f;
    	}
    	
    	float progress = 0;
    	switch (targetType) {
        case TIME:
        	for (Workout workout : workouts) {
        		System.out.println("added workout to progress: " + workout.getTitle());
        		progress += workout.getDuration();
        	}
            break;
        case DISTANCE:
        	for (Workout workout : workouts) {
        		System.out.println("added workout to progress: " + workout.getTitle());

        		progress += workout.getDistance();
        	}
            break;
        default:
            return 0f;
           
    	}
		System.out.println("target is: " + target);

		System.out.println("progress is: " + progress);
		System.out.println("target is: " + target);

		System.out.println("target maybe met");
      
	    if (progress >= target) {	
	    	System.out.println("target met1");
	    	userChallengeRepository.markAsFinished(userChallengeId);
			System.out.println("target met");
		}
		
		
        // If the result is null, return 0f
        return (progress/target*100f);
    
}
}

