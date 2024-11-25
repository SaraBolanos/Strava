package es.deusto.sd.strava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.User;


@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long>  {
	
	List<Challenge> findByCreator(User user);
	
	Challenge findById(long id);
	
	
}