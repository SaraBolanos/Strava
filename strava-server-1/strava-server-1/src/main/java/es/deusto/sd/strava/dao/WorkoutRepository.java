package es.deusto.sd.strava.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.Workout;
import es.deusto.sd.strava.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    // Find workouts by creator (user who created the workout)
    @Query("SELECT w FROM Workout w WHERE w.user = :user")
    List<Workout> findByUser(@Param("user") User user);
    
    

    // Find workout by id
    @Query("SELECT w FROM Workout w WHERE w.id = :id")
    Optional<Workout> findById(@Param("id") long id);
}
