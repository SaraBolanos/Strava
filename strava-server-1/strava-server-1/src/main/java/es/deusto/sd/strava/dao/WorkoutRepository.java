package es.deusto.sd.strava.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.Workout;


@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long>  {
	
	//List<Workout> FindByCreator(String Creator);
	
	//Workout findById(long id);
	
	
}