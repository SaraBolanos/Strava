package es.deusto.sd.strava.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.entity.Workout;
import es.deusto.sd.strava.enums.Sport;

@Service
public class WorkoutService {

    private final ArrayList<Workout> workoutList = new ArrayList<>();

    // Create a new workout with a user and optional creator
    public Workout createWorkout(int id, String title, Sport sport, float distance, Date date, LocalTime startTime, float duration, User user) {
        // Create a new Workout object with the provided data
        Workout workout = new Workout(id, title, sport, distance, date, startTime, duration, user);
        workoutList.add(workout);
        System.out.println("Added workout: " + workout.getTitle() + " at " + workout.getStartTime());
        return workout;
    }

    // Get all workouts
    public List<Workout> getAllWorkouts() {
        return new ArrayList<>(workoutList); // Return a copy to avoid external modifications
    }

    // Get filtered workouts by user, date, and sport (including creator)
    public List<Workout> getFilteredWorkouts(User user, String dateString, Sport sport) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate filterDate = dateString != null ? LocalDate.parse(dateString, formatter) : null;

        return workoutList.stream()
                .filter(workout -> workout.getUser().equals(user) || (workout.getUser() != null && workout.getUser().equals(user))) // Include workouts by the creator as well
                .filter(workout -> sport == null || sport.equals(workout.getSport())) // Filter by sport if specified
                .filter(workout -> filterDate == null || !workout.getStartDate().isAfter(filterDate)) // Filter by date if specified
                .collect(Collectors.toList());
    }

    // Get workouts by a specific user (including workouts by the creator)
    public List<Workout> getWorkoutsByUser(User user) {
        return workoutList.stream()
                .filter(workout -> workout.getUser().equals(user) || (workout.getUser() != null && workout.getUser().equals(user))) // Check if the user is the creator or the person doing the workout
                .collect(Collectors.toList());
    }

    // Get a specific workout by ID
    public Workout getWorkoutById(long id) {
        return workoutList.stream()
                .filter(workout -> workout.getId() == id)
                .findFirst()
                .orElse(null); // Return null if the workout is not found
    }

    // Delete a workout by ID
    public boolean deleteWorkout(long id) {
        return workoutList.removeIf(workout -> workout.getId() == id); // Remove if a workout with the given ID exists
    }

    // Get unfinished workouts (those that have not completed yet)
    public List<Workout> getUnfinishedWorkouts(User user) {
        // Assuming unfinished workouts are those that haven't reached the target distance or duration yet
        return workoutList.stream()
                .filter(workout -> workout.getUser().equals(user) || (workout.getUser() != null && workout.getUser().equals(user))) // Include both user and creator
                .filter(workout -> workout.getDistance() < 10) // Example condition: less than 10 km completed
                .collect(Collectors.toList());
    }
}
