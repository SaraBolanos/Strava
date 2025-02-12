package es.deusto.sd.strava.dto;

import java.util.Optional;

public class UserDTO {

	private String username;
	private String token;

	private Optional<Float> weight; //in kg
	private Optional<Float> height; //in cm
	private Optional<Integer> maxheartRate; //bpm
	private Optional<Integer> restHeartRate; //bpm
	
	public UserDTO() {
		
	}
	
	public UserDTO(String username, String token, Optional<Float> weight, Optional<Float> height,
			Optional<Integer> maxheartRate, Optional<Integer> restHeartRate) {
		super();
		this.username = username;
		this.token = token;
		this.weight = weight;
		this.height = height;
		this.maxheartRate = maxheartRate;
		this.restHeartRate = restHeartRate;
	}



	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public Optional<Float> getWeight() {
		return weight;
	}
	public void setWeight(Optional<Float> weight) {
		this.weight = weight;
	}
	public Optional<Float> getHeight() {
		return height;
	}
	public void setHeight(Optional<Float> height) {
		this.height = height;
	}
	public Optional<Integer> getMaxheartRate() {
		return maxheartRate;
	}
	public void setMaxheartRate(Optional<Integer> maxheartRate) {
		this.maxheartRate = maxheartRate;
	}
	public Optional<Integer> getRestHeartRate() {
		return restHeartRate;
	}
	public void setRestHeartRate(Optional<Integer> restHeartRate) {
		this.restHeartRate = restHeartRate;
	}
	
	
	
}
