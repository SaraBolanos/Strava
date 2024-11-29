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
	
	
	@Query(value = "SELECT uc.* FROM user_challenge uc " +
            "JOIN challenge c ON uc.challenge_id = c.id " +
            "WHERE TO_DATE(c.start_date, 'YYYY-MM-DD') <= CURRENT_DATE " +
            "AND TO_DATE(c.end_date, 'YYYY-MM-DD') >= CURRENT_DATE " +
            "AND c.sport = :sport", 
    nativeQuery = true)		//esto necesito para usar TO_DATE(x, 'YYYY-MM-DD'), para compararlo
List<UserChallenge> findActiveChallengesBySport(@Param("sport") String sport);
	
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
    @Query(value = "SELECT uc.* FROM user_challenge uc " +
            "JOIN challenge c ON uc.challenge_id = c.id " +
            "WHERE TO_DATE(c.start_date, 'YYYY-MM-DD') <= CURRENT_DATE " +
            "AND TO_DATE(c.end_date, 'YYYY-MM-DD') >= CURRENT_DATE", 
    nativeQuery = true) //esto necesito para usar TO_DATE(x, 'YYYY-MM-DD'), para compararlo
    List<Challenge> findActiveChallenges();
    
}