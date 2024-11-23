package es.deusto.sd.strava.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.entity.Workout;
import es.deusto.sd.strava.enums.Sport;

@Service
public class WorkoutService {

    public final ArrayList<Workout> workoutList = new ArrayList<>();

    // Create a new workout
    public Workout createWorkout(String title, Sport sport, float distance,Date date, User user) {
        Workout workout = new Workout(title, sport, distance, date, user);
        workoutList.add(workout);
        System.out.println("Added workout: " + workout.getTitle());
        return workout;
    }

    // Get all workouts
    public List<Workout> getAllWorkouts() {
        return workoutList;
    }

    // Get filtered workouts by date and sport
    public List<Workout> getFilteredWorkouts(String dateString, Sport sport) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate filterDate = dateString != null ? LocalDate.parse(dateString, formatter) : null;

        return workoutList.stream()
                .filter(workout -> sport == null || sport.equals(workout.getSport())) // Filter by sport if specified
                .filter(workout -> {
                    if (filterDate == null) return true; // If no date is specified, include all
                    LocalDate workoutDate = workout.getStartDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate(); // Convert Date to LocalDate
                    return !workoutDate.isAfter(filterDate); // Check if workoutDate is on or before filterDate
                })
                .collect(Collectors.toList());
    }
    // Get workouts by a specific user
    public List<Workout> getWorkoutsByUser(User user) {
        return workoutList.stream()
                .filter(workout -> workout.getUser().equals(user))
                .collect(Collectors.toList());
    }

    // Get a specific workout by ID
    public Workout getWorkoutById(long id) {
        return workoutList.stream()
                .filter(workout -> workout.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Delete a workout by ID
    public boolean deleteWorkout(long id) {
        return workoutList.removeIf(workout -> workout.getId() == id);
    }
}
