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
    @Query(value = "SELECT * FROM Workout w WHERE w.user = :user",nativeQuery = true)
    List<Workout> findByUser(@Param("user") User user);
    
    

    // Find workout by id
    @Query(value = "SELECT * FROM Workout w WHERE w.id = :id", nativeQuery = true)
    Optional<Workout> findById(@Param("id") long id);
    
    
}
