/**
 * This code is based on solutions provided by my brain and 
 * adapted using coffee. It has been kinda reviewed 
 * and validated to ensure some correctness and that it is mostly free of errors.
*/
package es.deusto.sd.strava.entity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class User {
	
	private String username;
	private String email;
	private ArrayList<UserChallenge> challenges = new ArrayList<UserChallenge>();
	private String token; //Token returned by google or facebook
	private Optional<Float> weight; //in kg
	private Optional<Float> height; //in cm
	private Optional<Integer> maxheartRate; //bpm
	private Optional<Integer> restHeartRate; //bpm
	
	// Constructor without parameters
	public User() { }
	
	// Constructor with parameters
	public User(String username, String email, String token) {
		this.setUsername(username);;		
		this.setEmail(email);
	}

	public User(String username, String email, String token,
			Optional<Float> weight, Optional<Float> height, Optional<Integer> maxheartRate,
			Optional<Integer> restHeartRate) {
		super();
		this.username = username;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public ArrayList<UserChallenge> getChallenges() {
		return challenges;
	}

	public void setChallenges(ArrayList<UserChallenge> challenges) {
		this.challenges = challenges;
	}
	public void addChallenge(Challenge challenge) {
		this.challenges.add(new UserChallenge(challenge));
	}

	// hashCode and equals
	@Override
	public int hashCode() {
		return Objects.hash(email, username);
	}
	
	public Optional<Float> getHeight(){
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