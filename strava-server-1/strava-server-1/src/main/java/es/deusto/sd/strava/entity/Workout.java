package es.deusto.sd.strava.entity;



import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;



import es.deusto.sd.strava.enums.Sport;

@Entity
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El ID se genera automáticamente
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Sport sport;

    private float distance;

    private LocalDate startDate; // Usamos LocalDate para representar la fecha

    private LocalTime startTime; // Usamos LocalTime para representar la hora de inicio

    private float duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User creator;

    // Constructor sin parámetros
    public Workout(int id, String title2, Sport sport2, float distance2, Date startDate2, LocalTime parsedStartTime, float duration2, User user2) {
        super();
    }

    // Constructor con parámetros
    public Workout(String title, Sport sport, float distance, LocalDate startDate, LocalTime startTime, float duration, User user) {
        this.title = title;
        this.sport = sport;
        this.distance = distance;
        this.startDate = startDate;
        this.startTime = startTime; // Asignación del nuevo parámetro
        this.duration = duration;
        this.creator = user;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public User getUser() {
        return creator;
    }

    public void setUser(User user) {
        this.creator = user;
    }

	
}
