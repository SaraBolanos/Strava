package es.deusto.sd.strava.facade;

import java.util.List;

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

@RestController
public class ChallengeController {

	private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
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
    @PostMapping
    public ResponseEntity<Challenge> createChallenge(@RequestBody Challenge challenge) {  //necesitamos un creator?
        Challenge newChallenge = challengeService.createChallenge(challenge);
        
        return new ResponseEntity<>(newChallenge, HttpStatus.CREATED);
    }
    @PostMapping("/challenges/{challengeId}")
    public ResponseEntity<Challenge> acceptChallenge(@PathVariable("challengeId") long challengeId, @RequestBody String userToken) {
    	User user = new User();  //en vez de esto luego busca el user con este userToken  //pasamos el usertoken y buscamos el usario entonces!!??
        Challenge challenge = challengeService.acceptChallenge(challengeId, user);
        
        return new ResponseEntity<>(challenge, HttpStatus.CREATED);
    }
    
    @GetMapping("/challenges/{userToken}")	
    public ResponseEntity<List<Challenge>> getAcceptedChallenges(@PathVariable("userToken") long userToken) {
            User user = new User(); //en vez de esto luego busca el user con este userToken                 
        return new ResponseEntity<>(challengeService.getAcceptedChallenges(user), HttpStatus.OK);
    }
    
    @GetMapping("/challenges/{userToken}/unfinished")	
    public ResponseEntity<List<Challenge>> getUnfinishedChallenges(@PathVariable("userToken") long userToken) {
            User user = new User(); //en vez de esto luego busca el user con este userToken                 
        return new ResponseEntity<>(challengeService.getUnfinishedChallenges(user), HttpStatus.OK);
    }
    
    @GetMapping("/challenges/{challengeId}/{userToken}/percentage")	
    public ResponseEntity<Float> getPercentageOfChallenge(@PathVariable("userToken") long userToken, @PathVariable("challengeId") long challengeId ) {
            User user = new User(); //en vez de esto luego busca el user con este userToken   
           
            return new ResponseEntity<>(challengeService.getPercentageOfAchievement(challengeId, user), HttpStatus.OK);
    }
    
    
    
    
    
}
