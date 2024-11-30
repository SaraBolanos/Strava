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

    // Get accepted challenges for a user
    public List<Challenge> getAcceptedChallenges(User user) {
        return userChallengeRepository.findAcceptedUserChallenges(user.getId());
    }

    // Get unfinished challenges for a user
    public List<Challenge> getUnfinishedChallenges(User user) {
    	return userChallengeRepository.findUnfinishedChallengesForUser(user.getId());
    }

    // Accept a challenge
    public Challenge acceptChallenge(long id, User user) {
        Optional<Challenge> challengeToAccept = challengeRepository.findById(id);

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
}
