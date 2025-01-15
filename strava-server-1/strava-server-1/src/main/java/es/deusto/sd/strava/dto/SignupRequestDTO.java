package es.deusto.sd.strava.dto;

import java.util.Optional;

public class SignupRequestDTO {
    private String email;
    private String password;
    private String method;
    private String username;
    private Optional<Float> weight = Optional.empty();
    private Optional<Float> height = Optional.empty();
    private Optional<Integer> maxHeartRate = Optional.empty();
    private Optional<Integer> restHeartRate = Optional.empty();

    public SignupRequestDTO() {
    	
    }
    
    public SignupRequestDTO(String email, String password, String method, String username, Optional<Float> weight,
			Optional<Float> height, Optional<Integer> maxHeartRate, Optional<Integer> restHeartRate) {
		super();
		this.email = email;
		this.password = password;
		this.method = method;
		this.username = username;
		this.weight = weight;
		this.height = height;
		this.maxHeartRate = maxHeartRate;
		this.restHeartRate = restHeartRate;
	}

	// Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Optional<Integer> getMaxHeartRate() {
        return maxHeartRate;
    }

    public void setMaxHeartRate(Optional<Integer> maxHeartRate) {
        this.maxHeartRate = maxHeartRate;
    }

    public Optional<Integer> getRestHeartRate() {
        return restHeartRate;
    }

    public void setRestHeartRate(Optional<Integer> restHeartRate) {
        this.restHeartRate = restHeartRate;
    }
}
