package es.deusto.sd.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.Challenge;


@Repository
public interface ChallengeDAO extends JpaRepository<Challenge, Long>  {
	
	List<Challenge> FindByCreator(String Creator);
	
	Challenge findById(long id);
	
	
}