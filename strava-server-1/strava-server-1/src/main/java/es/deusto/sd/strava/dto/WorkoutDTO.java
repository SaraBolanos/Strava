package es.deusto.sd.strava.dto;

import java.time.LocalTime;
import java.util.Date;

import es.deusto.sd.strava.enums.Sport;

public class WorkoutDTO {
	
	private String Title;
	private Sport sport;
	private float distance;
	private Date StartDate;
	private LocalTime startTime;
	private float duration;
	

	public WorkoutDTO() {
	}
	
	public WorkoutDTO(String title, Sport sport, float distance, Date startDate,LocalTime startTime,float duration) {
		super();
		Title = title;
		this.sport = sport;
		this.distance = distance;
		StartDate = startDate;
		this.startTime = startTime;
		this.duration = duration;
	}
	
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
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
	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	

}
