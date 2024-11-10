package es.deusto.sd.strava.entity;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import es.deusto.sd.strava.enums.Sport;

public class Workout {

	private String Title;
	private Sport sport;
	private float distance;
	private Date StartDate;
	private Date EndDate;
	private float duration;

	private static final AtomicLong counter = new AtomicLong(0);

	public Workout() {}
	
	public Workout(String title, Sport sport, float distance, Date startDate, Date endDate, float duration) {
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


	public static AtomicLong getCounter() {
		return counter;
	}


	
	@Override
	public int hashCode() {
	    return Objects.hash(Title, sport, distance, StartDate, EndDate, duration);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (getClass() != obj.getClass())
	        return false;
	    Workout other = (Workout) obj;
	    return Objects.equals(Title, other.Title) && sport == other.sport
	            && Float.floatToIntBits(distance) == Float.floatToIntBits(other.distance)
	            && Objects.equals(StartDate, other.StartDate)
	            && Objects.equals(EndDate, other.EndDate)
	            && Float.floatToIntBits(duration) == Float.floatToIntBits(other.duration);
	}

	
}
