package es.deusto.sd.strava.entity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    /*
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ArrayList<UserChallenge> challenges = new ArrayList<>();
    */

    @Column(nullable = true, unique = true)
    private String token; // Token returned by Google or Facebook

    // Use Float directly, nullable can represent the absence of a value
    @Column(nullable = true)
    private Float weight; // in kg

    // Use Float directly, nullable can represent the absence of a value
    @Column(nullable = true)
    private Float height; // in cm

    // Use Integer directly, nullable can represent the absence of a value
    @Column(nullable = true)
    private Integer maxHeartRate; // bpm

    // Use Integer directly, nullable can represent the absence of a value
    @Column(nullable = true)
    private Integer restHeartRate; // bpm

    // Constructor without parameters
    public User() {
    }
    

    // Constructor with parameters
    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.token = null;
    }
    /*
    public User() {
        this.username = "";
        this.email = "";
        this.token = "";
        this.weight = 0f;
        this.height = 0f;
        this.maxHeartRate = 0;
        this.restHeartRate = 0;
    }
    */

    public User(String username, String email, Optional<Float> weight, Optional<Float> height, Optional<Integer> maxHeartRate, Optional<Integer> restHeartRate) {
        this.username = username;
        this.email = email;
        this.token = null;
        this.weight = weight.orElse(null);
        this.height = height.orElse(null);
        this.maxHeartRate = maxHeartRate.orElse(null);
        this.restHeartRate = restHeartRate.orElse(null);
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return Optional.ofNullable(weight);
    }

    public void setWeight(Optional<Float> weight) {
        this.weight = weight.orElse(height);
    }
/*
    public ArrayList<UserChallenge> getChallenges() {
        return challenges;
    }
    */
    /*

    public void setChallenges(ArrayList<UserChallenge> challenges) {
        // Clear the existing collection to avoid replacing it
        this.challenges.clear();
        
        // Add all the new challenges while maintaining the bidirectional relationship
        for (UserChallenge challenge : challenges) {
            addChallenge(challenge);
        }
    }

    public void addChallenge(UserChallenge userChallenge) {
        // Add to the collection
        this.challenges.add(userChallenge);
        
        // Maintain bidirectional relationship
        userChallenge.setUser(this);
    }

    public void removeChallenge(UserChallenge userChallenge) {
        // Remove from the collection
        this.challenges.remove(userChallenge);
        
        // Break the bidirectional relationship
        userChallenge.setUser(null);
    }
    */


    public Optional<Float> getHeight() {
        return Optional.ofNullable(height);
    }

    public void setHeight(Optional<Float> height) {
        this.height = height.orElse(weight);
    }

    public Optional<Integer> getMaxHeartRate() {
        return Optional.ofNullable(maxHeartRate);
    }

    public void setMaxHeartRate(Optional<Integer> maxHeartRate) {
        this.maxHeartRate = maxHeartRate.orElse(null);
    }

    public Optional<Integer> getRestHeartRate() {
        return Optional.ofNullable(restHeartRate);
    }

    public void setRestHeartRate(Optional<Integer> restHeartRate) {
        this.restHeartRate = restHeartRate.orElse(null);
    }

    // hashCode and equals
    @Override
    public int hashCode() {
        return Objects.hash(email, username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(email, user.email) &&
               Objects.equals(username, user.username);
    }
}
