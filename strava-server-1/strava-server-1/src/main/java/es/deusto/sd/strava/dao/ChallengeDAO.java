package es.deusto.sd.strava.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.User;

@SpringBootApplication(scanBasePackages = "es.deusto.sd")


@Repository
public interface ChallengeDAO extends JpaRepository<Challenge, Long>  {
	
	public Optional<Challenge> findByCreator(User creator);

	public Optional<Challenge> findByCreator_Username(String username);

	Challenge findById(long id);
	
	
}