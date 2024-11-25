package es.deusto.sd.strava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.entity.Workout;


@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long>  {
	
	List<Workout> findByCreator(User creator);
	
	Workout findById(long id);
	
	
}