package es.deusto.sd.strava.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.Workout;
import es.deusto.sd.strava.enums.Sport;
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

   
    // Find workouts by sport type
    List<Workout> findBySport(Sport sport);

    // Find workouts performed on a specific date
    List<Workout> findByStartDate(LocalDate startDate);

    // Find workouts within a date range
    @Query("SELECT w FROM Workout w WHERE w.startDate BETWEEN :startDate AND :endDate")
    List<Workout> findByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // Find workouts for a specific user
    @Query("SELECT w FROM Workout w WHERE w.user.id = :userId")
    List<Workout> findByUserId(@Param("userId") long userId);

    // Find workouts by sport and minimum distance
    @Query("SELECT w FROM Workout w WHERE w.sport = :sport AND w.distance >= :minDistance")
    List<Workout> findBySportAndMinDistance(@Param("sport") Sport sport, @Param("minDistance") float minDistance);

    // Find workouts with duration less than or equal to a value
    List<Workout> findByDurationLessThanEqual(float maxDuration);

    // Find workouts by sport and specific date
    @Query("SELECT w FROM Workout w WHERE w.sport = :sport AND w.startDate = :date")
    List<Workout> findBySportAndDate(@Param("sport") Sport sport, @Param("date") LocalDate date);

    // Calculate total distance covered by a user
    @Query("SELECT SUM(w.distance) FROM Workout w WHERE w.user.id = :userId")
    Double findTotalDistanceByUser(@Param("userId") long userId);

    // Calculate total duration of workouts for a user
    @Query("SELECT SUM(w.duration) FROM Workout w WHERE w.user.id = :userId")
    Double findTotalDurationByUser(@Param("userId") long userId);

    // Count workouts performed by a user
    @Query("SELECT COUNT(w) FROM Workout w WHERE w.user.id = :userId")
    Long countWorkoutsByUser(@Param("userId") long userId);

    // Retrieve workouts with a specific distance range
    @Query("SELECT w FROM Workout w WHERE w.distance BETWEEN :minDistance AND :maxDistance")
    List<Workout> findByDistanceRange(@Param("minDistance") float minDistance, @Param("maxDistance") float maxDistance);

    // Retrieve workouts with a specific duration range
    @Query("SELECT w FROM Workout w WHERE w.duration BETWEEN :minDuration AND :maxDuration")
    List<Workout> findByDurationRange(@Param("minDuration") float minDuration, @Param("maxDuration") float maxDuration);

    // Find the longest workout for a user
    @Query("SELECT w FROM Workout w WHERE w.user.id = :userId ORDER BY w.distance DESC")
    List<Workout> findLongestWorkoutsByUser(@Param("userId") long userId);

    // Find the shortest workout for a user
    @Query("SELECT w FROM Workout w WHERE w.user.id = :userId ORDER BY w.distance ASC")
    List<Workout> findShortestWorkoutsByUser(@Param("userId") long userId);
}
