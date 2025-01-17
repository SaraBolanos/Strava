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
	
	
	@Query(value = "SELECT c.* FROM challenge c " +
            "WHERE CAST(c.start_date AS DATE) <= CURRENT_DATE " +
            "AND CAST(c.end_date AS DATE) >= CURRENT_DATE " +
            "AND c.sport = :sport", 
    nativeQuery = true)		//esto necesito para usar TO_DATE(x, 'YYYY-MM-DD'), para compararlo
List<Challenge> findActiveChallengesBySport(@Param("sport") String sport);
	
    @Query("SELECT c FROM Challenge c WHERE c.sport = :sport " +
  	       "AND :date BETWEEN c.startDate AND c.endDate")
  List<Challenge> findActiveChallengesBySportAndDate(@Param("sport") Sport sport, 
  	                                                   @Param("date") LocalDate date);
    
    // Find all active challenges for a specific user (challenge date is between start and end date)
    @Query(value= "SELECT c FROM challenge c WHERE CAST(c.startDate AS DATE) <= :date " +
           "AND CAST(c.endDate AS DATE) >= :date", nativeQuery = true)
    List<Challenge> findActiveChallengesByDate(LocalDate date);
    
    /*
    // Find all active challenges for a specific user (challenge date is between start and end date)
    @Query("SELECT uc FROM UserChallenge uc WHERE uc.challenge.startDate <= CURRENT_DATE " +
           "AND uc.challenge.endDate >= CURRENT_DATE")
    List<Challenge> findActiveChallenges();
    */
    @Query(value = "SELECT c.* FROM challenge c " +
            "WHERE CAST(c.start_date AS DATE) <= CURRENT_DATE " +
            "AND CAST(c.end_date AS DATE) >= CURRENT_DATE", 
    nativeQuery = true) //esto necesito para usar TO_DATE(x, 'YYYY-MM-DD'), para compararlo
    List<Challenge> findActiveChallenges();
    
    @Query(value = "SELECT * FROM challenge WHERE id = :challengeId", nativeQuery =true)
    Challenge findChallenge(long challengeId);
    
}