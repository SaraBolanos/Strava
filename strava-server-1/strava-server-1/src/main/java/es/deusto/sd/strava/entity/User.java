/**
 * This code is based on solutions provided by my brain and 
 * adapted using coffee. It has been kinda reviewed 
 * and validated to ensure some correctness and that it is mostly free of errors.
*/
package es.deusto.sd.strava.entity;

import java.util.Objects;

public class User {
	
	private String username;
	private String email;
	private String token;
	private float weight; //in kg
	private float height; //in cm
	private int maxheartRate; //bpm
	private int restHeartRate; //bpm
	
	// Constructor without parameters
	public User() { }
	
	// Constructor with parameters
	public User(String username, String email) {
		this.setUsername(username);		
		this.setEmail(email);
	}
	
	// Check if two users are the same. We consider 2 users are the same if  they have the same mail and username
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(getEmail(), other.getEmail()) && 
			   Objects.equals(getUsername(), other.getUsername());
	}
	
	//  Getters and setters
	public int getRestHeartRate() {
		return restHeartRate;
	}

	public void setRestHeartRate(int restHeartRate) {
		this.restHeartRate = restHeartRate;
	}

	public int getMaxheartRate() {
		return maxheartRate;
	}

	public void setMaxheartRate(int maxheartRate) {
		this.maxheartRate = maxheartRate;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}