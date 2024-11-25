package es.deusto.sd.strava.dto;


import java.time.LocalDate;


import es.deusto.sd.strava.enums.Sport;
import es.deusto.sd.strava.enums.TargetType;

public class ChallengeDTO {
	
	private long id; 
	private String name;
	private String startDate;
	private String endDate;
	private float target;
	private TargetType targetType;
	private Sport sport;
	private String creator; //only name
	
	public ChallengeDTO(Long long1, String string, LocalDate localDate, LocalDate localDate2, float f, TargetType targetType2, Sport sport2, String string2)
	{
		
	}

	public ChallengeDTO(long id, String name, String startDate, String endDate, float target, TargetType targetType,
			Sport sport, String creator) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.target = target;
		this.targetType = targetType;
		this.sport = sport;
		this.creator = creator;
	}
	
	


	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
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
	

	
	

}
