package es.deusto.sd.strava.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.deusto.sd.strava.dao.WorkoutRepository;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.entity.Workout;
import es.deusto.sd.strava.enums.Sport;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    // Creates a new workout and saves it to the database
    public Workout createWorkout(int id, String title, Sport sport, float distance, Date date, LocalTime startTime, float duration, User user) {
        Workout workout = new Workout(id, title, sport, distance, date, startTime, duration, user);
        return workoutRepository.save(workout); // Save workout in the database
    }

    // Retrieves all workouts from the database
    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    // Retrieves filtered workouts based on user, sport, and date (optional filters)
    public List<Workout> getFilteredWorkouts(User user, String filterDate, Sport sport) {
        List<Workout> userWorkouts = workoutRepository.findByUserId(user.getId()); // Fetch workouts for the user

        return userWorkouts.stream()
                .filter(workout -> sport == null || sport.equals(workout.getSport())) // Filter by sport if specified
                .filter(workout -> {
                    if (filterDate == null) return true; // Include all workouts if no date filter is provided
                    
                    try {
                        LocalDate parsedDate = LocalDate.parse(filterDate); // Parse the filter date
                        LocalDate workoutDate = workout.getStartDate().toLocalDate(); // Convert SQL Date to LocalDate
                        return !workoutDate.isAfter(parsedDate); // Include workouts on or before the filter date
                    } catch (Exception e) {
                        
                        return false; // Exclude workouts if the date format is invalid
                    }
                })
                .toList();
    }


    // Retrieves all workouts created by a specific user
    public List<Workout> getWorkoutsByUser(User user) {
        return workoutRepository.findByUser(user);
    }

    // Retrieves a specific workout by its ID
    public Optional<Workout> getWorkoutById(long id) {
        return workoutRepository.findById(id);
    }

    // Deletes a workout by its ID
    public boolean deleteWorkout(long id) {
        if (workoutRepository.existsById(id)) {
            workoutRepository.deleteById(id); // Remove the workout from the database
            return true;
        }
        return false;
    }
}
