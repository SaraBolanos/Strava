package es.deusto.sd.strava.entity;

import java.sql.Date;

import es.deusto.sd.strava.enums.Sport;

public class Workout {
	private long id;
	private String title;
	private Sport sport;
	private float distance;
	private Date startDate;
	private User user;
	
	
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Workout() {
		super();
	}
	public Workout(String title, Sport sport, float distance, Date startDate, User user) {
		super();
		this.title = title;
		this.sport = sport;
		this.distance = distance;
		this.startDate = startDate;
		this.user = user;
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
	
	
}
