package es.deusto.sd.strava.entity;


import java.sql.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import es.deusto.sd.strava.enums.Sport;
import es.deusto.sd.strava.enums.TargetType;

public class Challenge {
	private static final AtomicLong counter = new AtomicLong(0); 
	private long id;
	private String name;
	private String startDate;
	private String endDate;
	private float target;
	private TargetType targetType;
	private Sport sport;
	private User creator;
	
	public static AtomicLong getCounter() {
		return counter;
	}

	public Challenge() {}

	public Challenge(String name, String startDate, String endDate, float target, TargetType targetType, Sport sport, User user) {
		super();
		this.id = counter.incrementAndGet();   
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.target = target;
		this.targetType = targetType;
		this.sport = sport;
		this.creator = user;
	}
	
	//
	
	

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public float getTarget() {
		return target;
	}

	public void setTarget(float target) {
		this.target = target;
	}

	public TargetType getTargetType() {
		return targetType;
	}

	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creator, endDate, name, sport, startDate, target, targetType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Challenge other = (Challenge) obj;
		return Objects.equals(creator, other.creator) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(name, other.name) && sport == other.sport
				&& Objects.equals(startDate, other.startDate)
				&& Float.floatToIntBits(target) == Float.floatToIntBits(other.target) && targetType == other.targetType;
	}
	
	
	
	
	
	
	
	

}
