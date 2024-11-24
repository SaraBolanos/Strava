package es.deusto.sd.strava.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.entity.UserChallenge;
import es.deusto.sd.strava.enums.Sport;


@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long>  {
	
	
	
	

	//Optional<Challenge> findById(long id);
	
	 // Find all active challenges for a specific user and sport (challenge date is between start and end date)
    /*@Query("SELECT uc FROM UserChallenge uc WHERE uc.challenge.startDate <= CURRENT_DATE " +
           "AND uc.challenge.endDate >= CURRENT_DATE " +
           "AND uc.challenge.sport = :sport")
    List<Challenge> findActiveChallengesBySport(Sport sport);
    */
	@Query("SELECT uc FROM UserChallenge uc WHERE uc.challenge.sport = :sport")
	    List<Challenge> findActiveChallengesBySport(Sport sport);
	
    
    @Query("SELECT c FROM Challenge c WHERE c.sport = :sport " +
  	       "AND :date BETWEEN c.startDate AND c.endDate")
  List<Challenge> findActiveChallengesBySportAndDate(@Param("sport") Sport sport, 
  	                                                   @Param("date") LocalDate date);
    
    // Find all active challenges for a specific user (challenge date is between start and end date)
    @Query("SELECT uc FROM UserChallenge uc WHERE uc.challenge.startDate <= :date " +
           "AND uc.challenge.endDate >= :date")
    List<Challenge> findActiveChallengesByDate(LocalDate date);
    /*
    // Find all active challenges for a specific user (challenge date is between start and end date)
    @Query("SELECT uc FROM UserChallenge uc WHERE uc.challenge.startDate <= CURRENT_DATE " +
           "AND uc.challenge.endDate >= CURRENT_DATE")
    List<Challenge> findActiveChallenges();
    */
    
    @Query("SELECT uc FROM UserChallenge uc")		//später zurück wirft error
     List<Challenge> findActiveChallenges();
    
}