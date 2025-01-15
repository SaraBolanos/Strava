package es.deusto.sd.strava.client.data;


import java.util.Optional;

public class User {

	private String username;
	private String token;

	private Optional<Float> weight; //in kg
	private Optional<Float> height; //in cm
	private Optional<Integer> maxHeartRate; //bpm
	private Optional<Integer> restHeartRate; //bpm
	
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
		return maxHeartRate;
	}
	public void setMaxheartRate(Optional<Integer> maxheartRate) {
		this.maxHeartRate = maxheartRate;
	}
	public Optional<Integer> getRestHeartRate() {
		return restHeartRate;
	}
	public void setRestHeartRate(Optional<Integer> restHeartRate) {
		this.restHeartRate = restHeartRate;
	}
	
	
	
}

