package es.deusto.sd.strava.dto;

import java.util.ArrayList;

import es.deusto.sd.strava.entity.UserChallenge;

public class UserDTO {

	private String username;
	private String token;

	private ArrayList<UserChallenge> challenges;
	private float weight; //in kg
	private float height; //in cm
	private int maxheartRate; //bpm
	private int restHeartRate; //bpm
	
	
	
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
	public ArrayList<UserChallenge> getChallenges() {
		return challenges;
	}
	public void setChallenges(ArrayList<UserChallenge> challenges) {
		this.challenges = challenges;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public int getMaxheartRate() {
		return maxheartRate;
	}
	public void setMaxheartRate(int maxheartRate) {
		this.maxheartRate = maxheartRate;
	}
	public int getRestHeartRate() {
		return restHeartRate;
	}
	public void setRestHeartRate(int restHeartRate) {
		this.restHeartRate = restHeartRate;
	}
	
	
	
}
