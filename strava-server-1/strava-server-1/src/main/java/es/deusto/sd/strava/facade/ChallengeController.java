package es.deusto.sd.strava.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.deusto.sd.strava.dto.ChallengeDTO;
import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.entity.UserChallenge;
import es.deusto.sd.strava.entity.Workout;
import es.deusto.sd.strava.enums.Sport;
import es.deusto.sd.strava.enums.TargetType;
import es.deusto.sd.strava.service.ChallengeService;
import es.deusto.sd.strava.service.UserService;
import es.deusto.sd.strava.service.WorkoutService;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class ChallengeController {

	private final ChallengeService challengeService;
	private final UserService userService;
	 private final WorkoutService workoutService;
	
    public ChallengeController(ChallengeService challengeService, UserService userService, WorkoutService workoutService) {
        this.challengeService = challengeService;
        this.userService = userService;
        this.workoutService = workoutService;
    }
    
   

    @GetMapping("/challenges")
    public ResponseEntity<List<ChallengeDTO>> getChallenges(
            @RequestParam(value = "sport", required = false) Sport sport,
            @RequestParam(value = "date", required = false) String date) {
    	
    	List<Challenge> challenges = challengeService.getAllChallenges(date, sport);
		
        return new ResponseEntity<>(challengesToDTOs(challenges), HttpStatus.OK);
    }
    
    @GetMapping("/challenges/all")	///test!
    public ResponseEntity<List<ChallengeDTO>> getAllChallenges() {  
    	List<Challenge> challenges = challengeService.getAllChallengesTEST();
    	
        return new ResponseEntity<>(challengesToDTOs(challenges), HttpStatus.OK);
    }

    // Create a new challenge
    @PostMapping("/challenges")
    public ResponseEntity<ChallengeDTO> createChallenge(@RequestParam("userToken") String userToken,
    		@RequestBody ChallengeDTO challengeDTO){
		
    	Optional<User> user = userService.getUserByToken(userToken);
    	
    	//System.out.println("data:" + name + startDate + endDate + targetType + target + sport + userToken + user);
    	
    	
    	if (user == null) {
    		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    	}
    	
    	Challenge challenge = user
    	        .map(foundUser -> challengeService.createChallenge(challengeDTO.getName(), challengeDTO.getStartDate(), challengeDTO.getEndDate(), challengeDTO.getTarget(), challengeDTO.getTargetType(), challengeDTO.getSport(), foundUser))
    	        .orElse(null); 
    	
        //ChallengeDTO newChallengedto = challengesToDTO(challengeService.createChallenge(name, startDate, endDate, target, targetType, sport, user));
       
        return new ResponseEntity<>(challengeToDTO(challenge), HttpStatus.CREATED);
    }
    
    
    @PostMapping("/challenges/{challengeId}")
    public ResponseEntity<ChallengeDTO> acceptChallenge(@PathVariable("challengeId") long challengeId, @Parameter(name = "token", description = "Authorization token", required = true, example = "1727786726773") @RequestParam String userToken) {
    	//User user = new User();  //en vez de esto luego busca el user con este userToken  //pasamos el usertoken y buscamos el usario entonces!!??
    	Optional<User> user = userService.getUserByToken(userToken);
    	
    	Challenge challenge = user
    	        .map(foundUser -> challengeService.acceptChallenge(challengeId, foundUser))
    	        .orElse(null); 
    	    
    	    if (challenge == null) {
    	        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    	    }

    	    return new ResponseEntity<>(challengeToDTO(challenge), HttpStatus.CREATED);
    }
    
    @GetMapping("/challenges/{userToken}")	
    public ResponseEntity<List<ChallengeDTO>> getAcceptedChallenges(@PathVariable("userToken") String userToken) {
            //User user = new User(); //en vez de esto luego busca el user con este userToken          
    	Optional<User> user = userService.getUserByToken(userToken);
    	List<Challenge> challenges = user
        	        .map(foundUser -> challengeService.getAcceptedChallenges(foundUser))
        	        .orElse(null); 
        	    
        	if (challenges == null) {
    	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    	    }
        return new ResponseEntity<>(challengesToDTOs(challenges), HttpStatus.OK);
    }
    @GetMapping("/challenge/{challengeId}")	
    public ResponseEntity<ChallengeDTO> getChallenge(@PathVariable("challengeId") long challengeId) {
    		Challenge challenge = challengeService.getChallenge(challengeId);
        	       
        	if (challenge == null) {
    	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    	    }
        return new ResponseEntity<>(challengeToDTO(challenge), HttpStatus.OK);
    }
    
    
    
    @GetMapping("/challenges/{userToken}/unfinished")	
    public ResponseEntity<List<ChallengeDTO>> getUnfinishedChallenges(@PathVariable("userToken") String userToken) {
            //User user = new User(); //en vez de esto luego busca el user con este userToken 
    	Optional<User> user = userService.getUserByToken(userToken);
    	List<Challenge> challenges = user
        	        .map(foundUser -> challengeService.getUnfinishedChallenges(foundUser))
        	        .orElse(null); 
        	    
        	if (challenges == null) {
    	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    	    }
        return new ResponseEntity<>(challengesToDTOs(challenges), HttpStatus.OK);
    }
    
    @GetMapping("/challenge/{challengeId}/percentage")
    public ResponseEntity<Float> getPercentageOfChallenge(
        @PathVariable("challengeId") long challengeId,
        @RequestParam("token") String userToken) {   
    	System.out.println("helloo");
    	Optional<User> user = userService.getUserByToken(userToken);
    	Challenge challenge;
    	challenge=challengeService.getChallenge(challengeId);
    	UserChallenge userchallenge;
    	userchallenge = challengeService.getUserChallenge(challengeId);
    	if (userchallenge == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
	    }  
    	if (challenge == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
	    }    		
    	List<Workout> workouts = user.map(foundUser -> {
    		        // Ensure the workouts list is not null and handle it
    		        return workoutService.getFilteredWorkouts2(foundUser, challenge.getStartDate(), challenge.getEndDate(), challenge.getSport());
    		    })
    			.orElse(Collections.emptyList());
    	
    		float percentage = challengeService.getPercentageOfAchievement2(workouts, challenge.getTarget(), challenge.getTargetType(), userchallenge.getId());
    	
        	if (percentage == -1f) {
    	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    	    }
        return new ResponseEntity<>(percentage, HttpStatus.OK);
    }
    
 // Converts an Challenge to an ChallengeDTO
 	private ChallengeDTO challengeToDTO(Challenge challenge) {
 		return new ChallengeDTO(challenge.getId(), challenge.getName(), challenge.getStartDate(), challenge.getEndDate(), challenge.getTarget(), challenge.getTargetType(), challenge.getSport(), challenge.getCreator().getUsername());
 	}
 	
 
 	
 	private List<ChallengeDTO> challengesToDTOs(List<Challenge> challenges) {
 	List<ChallengeDTO> ctos = new ArrayList<>();
	challenges.forEach(category -> ctos.add(challengeToDTO(category)));
	return ctos;
 	}
 	
    
    
    
    
}
