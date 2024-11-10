package es.deusto.sd.strava.facade;

import java.util.ArrayList;
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
import es.deusto.sd.strava.enums.Sport;
import es.deusto.sd.strava.enums.TargetType;
import es.deusto.sd.strava.service.ChallengeService;
import es.deusto.sd.strava.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class ChallengeController {

	private final ChallengeService challengeService;
	private final UserService userService;
	
    public ChallengeController(ChallengeService challengeService, UserService userService) {
        this.challengeService = challengeService;
        this.userService = userService;
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
    	List<Challenge> challenges = challengeService.getAllChallengesTest();
    	
        return new ResponseEntity<>(challengesToDTOs(challenges), HttpStatus.OK);
    }

    // Create a new challenge
    @PostMapping("/challenges")
    public ResponseEntity<ChallengeDTO> createChallenge(
    		@Parameter(name = "name", description = "name of the challenge", required = true)		
    		@RequestParam("name") String name,
			@Parameter(name = "startDate", description = "start date of the challenge in format yyyy-MM-dd", required = true, example = "2022-11-22")
    		@RequestParam("startDate") String startDate,
    		@Parameter(name = "endDate", description = "end date of the challenge in format yyyy-MM-dd", required = true, example = "2024-10-08")
			@RequestParam("endDate") String endDate,
			
			@Parameter(name = "targetType", description = "the target type can be Kilometers or Time", required = true, example = "TIME")
			@RequestParam("targetType") TargetType targetType,
			@Parameter(name = "target", description = "the number of kilometers or minutes", required = true, example = "10")
			@RequestParam("target") float target,
			@Parameter(name = "sport", description = "sport that can be Cycling or Running", required = true, example = "Running")
			@RequestParam("sport") Sport sport,
    		@Parameter(name = "token", description = "Authorization token", required = true, example = "1727786726773")
    		@RequestBody String userToken){
		
    	Optional<User> user = userService.getUserByToken(userToken);
    	
    	System.out.println("data:" + name + startDate + endDate + targetType + target + sport + userToken + user);
    	
    	
    	if (user == null) {
    		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    	}
    	
    	Challenge challenge = user
    	        .map(foundUser -> challengeService.createChallenge(name, startDate, endDate, target, targetType, sport, foundUser))
    	        .orElse(null); 
    	
        //ChallengeDTO newChallengedto = challengesToDTO(challengeService.createChallenge(name, startDate, endDate, target, targetType, sport, user));
       
        return new ResponseEntity<>(challengeToDTO(challenge), HttpStatus.CREATED);
    }
    
    
    @PostMapping("/challenges/{challengeId}")
    public ResponseEntity<ChallengeDTO> acceptChallenge(@PathVariable("challengeId") long challengeId, @Parameter(name = "token", description = "Authorization token", required = true, example = "1727786726773") @RequestBody String userToken) {
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
    
    @GetMapping("/challenges/{challengeId}/{userToken}/percentage")	
    public ResponseEntity<Float> getPercentageOfChallenge(@PathVariable("userToken") String userToken, @PathVariable("challengeId") long challengeId ) {
            //User user = new User(); //en vez de esto luego busca el user con este userToken   
    	Optional<User> user = userService.getUserByToken(userToken);
    	float percentage = user
        	        .map(foundUser -> challengeService.getPercentageOfAchievement(challengeId, foundUser))
        	        .orElse(-1f); //porque no hay null y no puedo usar 0
        	    
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
