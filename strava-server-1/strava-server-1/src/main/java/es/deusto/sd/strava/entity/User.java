/**
 * This code is based on solutions provided by my brain and 
 * adapted using coffee. It has been kinda reviewed 
 * and validated to ensure some correctness and that it is mostly free of errors.
*/
package es.deusto.sd.strava.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import jakarta.persistence.Id;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertFalse;


@Entity
@Table(name = "users")
public class User {

    
    
    @Id
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true, columnDefinition = "FLOAT")
    private Float weight; // in kg

    @Column(nullable = true, columnDefinition = "FLOAT")
    private Float height; // in cm

    @Column(nullable = true, columnDefinition = "INTEGER")
    private Integer maxHeartRate; // bpm

    @Column(nullable = true, columnDefinition = "INTEGER")
    private Integer restHeartRate; // bpm

    @Column(nullable = true)
    private String token; // Token returned by Google or Facebook



	
	
	
	
	// Constructor without parameters
	public User() { }
	
	// Constructor with parameters
	public User(String username, String email) {
		this.setUsername(username);;		
		this.setEmail(email);
		this.token = null;
	}

	public User(String username, String email,
			Float weight, Float height, Integer maxheartRate,
			Integer restHeartRate) {
		super();
		this.username = username;
		this.email = email;
		this.token = null;
		this.weight = weight;
		this.height = height;
		this.maxHeartRate = maxheartRate;
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

	

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Integer getMaxHeartRate() {
		return maxHeartRate;
	}

	public void setMaxHeartRate(Integer maxHeartRate) {
		this.maxHeartRate = maxHeartRate;
	}

	public Integer getRestHeartRate() {
		return restHeartRate;
	}

	public void setRestHeartRate(Integer restHeartRate) {
		this.restHeartRate = restHeartRate;
	}

	
	
	
}