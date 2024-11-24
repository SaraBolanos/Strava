/*package es.deusto.sd.strava.facade;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.deusto.sd.strava.dto.WorkoutDTO;
import es.deusto.sd.strava.entity.Workout;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.enums.Sport;
import es.deusto.sd.strava.service.WorkoutService;
import es.deusto.sd.strava.service.UserService;

@RestController
public class WorkoutController {

    private final WorkoutService workoutService;
    private final UserService userService;

    public WorkoutController(WorkoutService workoutService, UserService userService) {
        this.workoutService = workoutService;
        this.userService = userService;
    }

    // Get workouts for a specific user by token
    @GetMapping("/user/workouts")
    public ResponseEntity<List<WorkoutDTO>> getUserWorkouts(
            @RequestParam(value = "userToken", required = true) String userToken,
            @RequestParam(value = "sport", required = false) Sport sport,
            @RequestParam(value = "date", required = false) String date) {

        // Retrieve the user by token
        Optional<User> user = userService.getUserByToken(userToken);

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // User not found
        }

        List<Workout> workouts = workoutService.getFilteredWorkouts(user.get(), date, sport);
        return new ResponseEntity<>(workoutsToDTOs(workouts), HttpStatus.OK);
    }

    // Get all workouts for testing
    @GetMapping("/workouts/all") // Test endpoint
    public ResponseEntity<List<WorkoutDTO>> getAllWorkouts() {
        List<Workout> workouts = workoutService.getAllWorkouts();
        return new ResponseEntity<>(workoutsToDTOs(workouts), HttpStatus.OK);
    }

    // Create a new workout
    @PostMapping("/workouts")
    public ResponseEntity<WorkoutDTO> createWorkout(
            @RequestParam("title") String title,
            @RequestParam("distance") float distance,
            @RequestParam("sport") Sport sport,
            @RequestParam("duration") float duration,
            @RequestParam("date") String date,
            @RequestParam("startTime") String startTime, // Nuevo parámetro
            @RequestParam("userToken") String userToken) {

        // Retrieve the user by token
        Optional<User> user = userService.getUserByToken(userToken);

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Unauthorized if user is not found
        }

        // Parse the date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date startDate;
        try {
            startDate = new java.sql.Date(formatter.parse(date).getTime());
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Invalid date format
        }

        // Parse the start time
        LocalTime parsedStartTime;
        try {
            parsedStartTime = LocalTime.parse(startTime); // Convertir a LocalTime
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Invalid time format
        }

        // Create and save the workout
        Workout workout = new Workout(0, title, sport, distance, startDate, parsedStartTime, duration, user.get());
        workout = workoutService.createWorkout(0, title, sport, distance, startDate, parsedStartTime, duration, user.get());

        return new ResponseEntity<>(workoutToDTO(workout), HttpStatus.CREATED);
    }

    // Converts a Workout to a WorkoutDTO
    private WorkoutDTO workoutToDTO(Workout workout) {
        return new WorkoutDTO(
                workout.getTitle(),
                workout.getSport(),
                workout.getDistance(),
                workout.getStartDate(),
                workout.getStartTime(), // Incluir hora de inicio
                workout.getDuration());
    }

    // Converts a list of Workouts to a list of WorkoutDTOs
    private List<WorkoutDTO> workoutsToDTOs(List<Workout> workouts) {
        List<WorkoutDTO> dtos = new ArrayList<>();
        workouts.forEach(workout -> dtos.add(workoutToDTO(workout)));
        return dtos;
    }
}*/
