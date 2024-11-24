package es.deusto.sd.strava.dao;

import java.time.LocalDate;
import java.util.Date;
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
public interface UserChallengeRepository extends JpaRepository<UserChallenge, Long> {
    
    // Find all UserChallenges associated with a specific user
    List<UserChallenge> findByUser(User user);

    // Find a UserChallenge by user and challenge IDs
    Optional<UserChallenge> findByUserAndChallengeId(User user, long challengeId);
    
    // Find all UserChallenges for a specific challenge (if needed)
    List<UserChallenge> findByChallengeId(long challengeId);
    
    @Query("SELECT c FROM Challenge c JOIN UserChallenge uc ON uc.challenge.id = c.id WHERE uc.user.id = :userId AND uc.finished = false")
    List<Challenge> findUnfinishedChallengesForUser(@Param("userId") Long userId);

    
    @Query("SELECT c FROM Challenge c JOIN UserChallenge uc ON uc.challenge.id = c.id WHERE uc.user.id = :userId")
    List<Challenge> findAcceptedUserChallenges(@Param("userId") Long userId);


    @Query("SELECT (uc.progress / c.target) * 100 FROM UserChallenge uc JOIN uc.challenge c WHERE uc.user.id = :userId AND c.id = :challengeId")
    float getPercentageOfAchievement(@Param("userId") Long userId, @Param("challengeId") long challengeId);


}

 