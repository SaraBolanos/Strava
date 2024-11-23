package es.deusto.sd.strava.facade;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.deusto.sd.strava.dto.WorkoutDTO;
import es.deusto.sd.strava.entity.Workout;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.enums.Sport;
import es.deusto.sd.strava.service.WorkoutService;
import es.deusto.sd.strava.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class WorkoutController {

    private final WorkoutService workoutService;
    private final UserService userService;

    public WorkoutController(WorkoutService workoutService, UserService userService) {
        this.workoutService = workoutService;
        this.userService = userService;
    }

    // Get workouts for a specific user
    @GetMapping("/user/{userId}/workouts")
    public ResponseEntity<List<WorkoutDTO>> getUserWorkouts(
            @PathVariable("userId") long userId, 
            @RequestParam(value = "sport", required = false) Sport sport,
            @RequestParam(value = "date", required = false) String date) {

        Optional<User> user = userService.getUserById(userId); //Esto no se como conseguir el User
        
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // User not found
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
            @Parameter(name = "title", description = "title of the workout", required = true)
            @RequestParam("title") String title,
            @Parameter(name = "distance", description = "distance of the workout in kilometers", required = true, example = "5.5")
            @RequestParam("distance") float distance,
            @Parameter(name = "sport", description = "sport type", required = true, example = "Running")
            @RequestParam("sport") Sport sport,
            @Parameter(name = "duration", description = "duration of the workout in minutes", required = true, example = "60")
            @RequestParam("duration") float duration,
            @Parameter(name = "date", description = "start date of the workout in format yyyy-MM-dd", required = true, example = "2024-11-22")
            @RequestParam("date") String date,
            @Parameter(name = "token", description = "Authorization token", required = true, example = "1727786726773")
            @RequestBody String userToken) {

        Optional<User> user = userService.getUserByToken(userToken);

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);  // Unauthorized if user is not found
        }

        // Convert the string date into a java.sql.Date object
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date startDate = null;
        try {
            startDate = new java.sql.Date(formatter.parse(date).getTime());
        } catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Invalid date format
        }

        Workout workout = new Workout(id, title, sport, distance, startDate, duration, user.get());  // Assuming '0' as a placeholder for the ID
        workout = workoutService.createWorkout(id, title, sport, distance, startDate, duration, user.get());  // Assuming your service saves the workout

        return new ResponseEntity<>(workoutToDTO(workout), HttpStatus.CREATED);
    }

    

    // Converts a Workout to a WorkoutDTO
    private WorkoutDTO workoutToDTO(Workout workout) {
        return new WorkoutDTO( workout.getTitle(), workout.getSport(),workout.getDistance(), workout.getStartDate(),  workout.getDuration());
    }

    // Converts a list of Workouts to a list of WorkoutDTOs
    private List<WorkoutDTO> workoutsToDTOs(List<Workout> workouts) {
        List<WorkoutDTO> dtos = new ArrayList<>();
        workouts.forEach(workout -> dtos.add(workoutToDTO(workout)));
        return dtos;
    }
}
