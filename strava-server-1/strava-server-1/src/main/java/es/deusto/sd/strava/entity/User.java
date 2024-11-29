package es.deusto.sd.strava.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class User {

	

	private String token; //Token, null if user is logged out.
	private Optional<Float> weight; //in kg
	private Optional<Float> height; //in cm
	private Optional<Integer> maxheartRate; //bpm
	private Optional<Integer> restHeartRate; //bpm
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El ID se genera automáticamente
    private Long id;

    private String username;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserChallenge> challenges = new ArrayList<>();

   
    @JoinColumn(name = "creator_id") // La columna que almacena la referencia al creador
    private User creator; // El creador es otro usuario

    // Constructor sin parámetros
    public User() { }

    // Constructor con parámetros
    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.token = null;
        this.challenges = new ArrayList<>(); // Inicializar la lista de desafíos
    }

    public User(String username, String email, Optional<Float> weight, Optional<Float> height,  Optional<Integer> maxHeartRate,  Optional<Integer> restHeartRate) {
        this.username = username;
        this.email = email;
        this.token = null;
        this.weight = weight;
        this.height = height;
        this.maxheartRate = maxHeartRate;
        this.restHeartRate = restHeartRate;
        this.challenges = new ArrayList<>(); // Inicializar la lista de desafíos
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = Optional.of(weight);
    }

    public Optional<Float> getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = Optional.ofNullable(height);
    }

    public Optional<Integer> getMaxheartRate() {
        return maxheartRate;
    }

    public void setMaxheartRate(Integer maxheartRate) {
        this.maxheartRate = Optional.ofNullable(maxheartRate);
    }

    public Optional<Integer> getRestHeartRate() {
        return restHeartRate;
    }

    public void setRestHeartRate(Integer restHeartRate) {
        this.restHeartRate = Optional.ofNullable(restHeartRate);
    }

    public List<UserChallenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<UserChallenge> challenges) {
        this.challenges = challenges;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    // Método para agregar un desafío
    public void addChallenge(UserChallenge userChallenge) {
        this.challenges.add(userChallenge);
    }

    // Sobrescritura de hashCode y equals
    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(username, user.username) &&
               Objects.equals(email, user.email);
    }
}
