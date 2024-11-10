package es.deusto.sd.strava.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.entity.UserChallenge;
import es.deusto.sd.strava.enums.Sport;
import es.deusto.sd.strava.enums.TargetType;

@Service
public class ChallengeService {

	public final ArrayList<Challenge> challengeList = new ArrayList<Challenge>();
	
		
	public Challenge createChallenge(String name, String startDate, String endDate, float target, TargetType targetType, Sport sport, User user) {
		Challenge challenge = new Challenge (name, startDate, endDate,target, targetType, sport, user);
		challengeList.add(challenge);
		System.out.println("added" + challenge.getName());
		return challenge;
	}
	
	public List<Challenge> getAllChallengesTest(){return challengeList;}
	
	
	public List<Challenge> getAllChallenges(String dateString, Sport sport){
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<Challenge> activeChallenges = challengeList.stream()
                .filter(challenge -> {
                    LocalDate start = LocalDate.parse(challenge.getStartDate(), formatter);
                    LocalDate end = LocalDate.parse(challenge.getEndDate(), formatter);
                    return (today.isEqual(start) || today.isAfter(start)) && (today.isEqual(end) || today.isBefore(end));
                })
                .collect(Collectors.toList());
		
		
		if (sport != null) {
			if (dateString!=null) { //filter por ambos
				return challengeList.stream()
			             .filter(challenge -> sport.equals(challenge.getSport()))
			             .filter(challenge -> {
			 	            LocalDate start = LocalDate.parse(challenge.getStartDate(), formatter);
			 	            LocalDate end = LocalDate.parse(challenge.getEndDate(), formatter);
			 	            LocalDate date = LocalDate.parse(dateString, formatter);
			 	            return (date.isEqual(start) || date.isAfter(start)) && (date.isEqual(end) || date.isBefore(end));
			 	        })
			             .collect(Collectors.toList());
				
			}
			else {	//filter only sport
				return activeChallenges.stream()
			             .filter(challenge -> sport.equals(challenge.getSport()))
			             .collect(Collectors.toList());
			}	
			
		}
		if(dateString!=null) {//filter por date
			challengeList.stream()
	        .filter(challenge -> {
	            LocalDate start = LocalDate.parse(challenge.getStartDate(), formatter);
	            LocalDate end = LocalDate.parse(challenge.getEndDate(), formatter);
	            LocalDate date = LocalDate.parse(dateString, formatter);
	            return (date.isEqual(start) || date.isAfter(start)) && (date.isEqual(end) || date.isBefore(end));
	        })
	        .collect(Collectors.toList());
		}
		
		
		return activeChallenges;
		
		 
}
	
	public List<Challenge> getAcceptedChallenges(User user){
		
		
		return user.getChallenges().stream()
                .map(UserChallenge::getChallenge)  // Get the Challenge from each UserChallenge
                .collect(Collectors.toList());
	
	}
	
	public List<Challenge> getUnfinishedChallenges(User user){
		
		
		return user.getChallenges().stream()
				.filter(challenge -> { return challenge.isFinished()==false;})
                .map(UserChallenge::getChallenge)  // Get the Challenge from each UserChallenge
                .collect(Collectors.toList());
	
	}
	
	public Challenge acceptChallenge(long id, User user){
		Optional<Challenge> challengeToAccept = challengeList.stream()
	            .filter(challenge -> challenge.getId() == id)
	            .findFirst();
		
		challengeToAccept.ifPresent(challenge -> user.addChallenge(challenge));

	    //challenge.ifPresent(user::addChallenge);
		
	    return challengeToAccept.orElse(null);  
	
	}
	
	public float getPercentageOfAchievement(long id, User user){
		Optional<UserChallenge> userChallengeToCheck = user.getChallenges().stream().filter(userChallenge -> {return userChallenge.getChallenge().getId()==id;}).findFirst();
		
		if (userChallengeToCheck.isPresent()) {
	        UserChallenge userChallenge = userChallengeToCheck.get();
	        
	        return (userChallenge.getProgress() / userChallenge.getChallenge().getTarget()) * 100;
	        
	    }
	    
	    return 0f;  // Return 0 if not present or if target is 0
		
			
	}
	
	
	
}
