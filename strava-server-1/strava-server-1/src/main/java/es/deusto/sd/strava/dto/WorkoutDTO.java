package es.deusto.sd.strava.dto;

import java.util.Date;

import es.deusto.sd.strava.enums.Sport;

public class WorkoutDTO {
	
	private String Title;
	private Sport sport;
	private float distance;
	private Date StartDate;
	private Date EndDate;
	private float duration;
	

	public WorkoutDTO() {
	}
	
	public WorkoutDTO(String title, Sport sport, float distance, Date startDate, Date endDate, float duration) {
		super();
		Title = title;
		this.sport = sport;
		this.distance = distance;
		StartDate = startDate;
		EndDate = endDate;
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
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	

}