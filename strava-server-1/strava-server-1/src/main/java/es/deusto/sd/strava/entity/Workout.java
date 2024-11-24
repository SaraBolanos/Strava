package es.deusto.sd.strava.entity;

import java.sql.Date;
import java.time.LocalTime;

import es.deusto.sd.strava.enums.Sport;

public class Workout {
    private long id;
    private String title;
    private Sport sport;
    private float distance;
    private Date startDate;
    private LocalTime startTime; // Cambiado a LocalTime para representar la hora de inicio
    private float duration;
    private User user;

    public Workout() {
        super();
    }

    public Workout(long id, String title, Sport sport, float distance, Date startDate, LocalTime startTime, float duration, User user) {
        super();
        this.id = id;
        this.title = title;
        this.sport = sport;
        this.distance = distance;
        this.startDate = startDate;
        this.startTime = startTime; // Asignación del nuevo parámetro
        this.duration = duration;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() { // Nuevo método getter
        return startTime;
    }

    public void setStartTime(LocalTime startTime) { // Nuevo método setter
        this.startTime = startTime;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}
