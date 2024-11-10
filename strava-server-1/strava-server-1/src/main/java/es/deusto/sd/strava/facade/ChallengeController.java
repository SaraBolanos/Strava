package es.deusto.sd.strava.facade;

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

import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.enums.Sport;
import es.deusto.sd.strava.service.ChallengeService;
import es.deusto.sd.strava.service.UserService;

@RestController
public class ChallengeController {

	private final ChallengeService challengeService;
	private final UserService userService;
	
    public ChallengeController(ChallengeService challengeService, UserService userService) {
        this.challengeService = challengeService;
        this.userService = userService;
    }
    
   

    @GetMapping("/challenges")
    public ResponseEntity<List<Challenge>> getChallenges(
            @RequestParam(value = "sport", required = false) Sport sport,
            @RequestParam(value = "date", required = false) String date) {             
        return new ResponseEntity<>(challengeService.getAllChallenges(date, sport), HttpStatus.OK);
    }
    
    @GetMapping("/challenges/all")	///test!
    public ResponseEntity<List<Challenge>> getAllChallenges() {             
        return new ResponseEntity<>(challengeService.getAllChallengesTest(), HttpStatus.OK);
    }

    // Create a new dish
    @PostMapping("/challenges")
    public ResponseEntity<Challenge> createChallenge(@RequestBody Challenge challenge) {  //necesitamos un creator?
        Challenge newChallenge = challengeService.createChallenge(challenge);
        
        return new ResponseEntity<>(newChallenge, HttpStatus.CREATED);
    }
    @PostMapping("/challenges/{challengeId}")
    public ResponseEntity<Challenge> acceptChallenge(@PathVariable("challengeId") long challengeId, @RequestBody String userToken) {
    	//User user = new User();  //en vez de esto luego busca el user con este userToken  //pasamos el usertoken y buscamos el usario entonces!!??
    	Optional<User> user = userService.getUserByToken(userToken);
    	Challenge challenge = user
    	        .map(foundUser -> challengeService.acceptChallenge(challengeId, foundUser))
    	        .orElse(null); 
    	    
    	    if (challenge == null) {
    	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    	    }

    	    return new ResponseEntity<>(challenge, HttpStatus.CREATED);
    }
    
    @GetMapping("/challenges/{userToken}")	
    public ResponseEntity<List<Challenge>> getAcceptedChallenges(@PathVariable("userToken") String userToken) {
            //User user = new User(); //en vez de esto luego busca el user con este userToken          
    	Optional<User> user = userService.getUserByToken(userToken);
    	List<Challenge> challenges = user
        	        .map(foundUser -> challengeService.getAcceptedChallenges(foundUser))
        	        .orElse(null); 
        	    
        	if (challenges == null) {
    	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    	    }
        return new ResponseEntity<>(challenges, HttpStatus.OK);
    }
    
    @GetMapping("/challenges/{userToken}/unfinished")	
    public ResponseEntity<List<Challenge>> getUnfinishedChallenges(@PathVariable("userToken") String userToken) {
            //User user = new User(); //en vez de esto luego busca el user con este userToken 
    	Optional<User> user = userService.getUserByToken(userToken);
    	List<Challenge> challenges = user
        	        .map(foundUser -> challengeService.getUnfinishedChallenges(foundUser))
        	        .orElse(null); 
        	    
        	if (challenges == null) {
    	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    	    }
        return new ResponseEntity<>(challenges, HttpStatus.OK);
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
    
    
    
    
    
}
