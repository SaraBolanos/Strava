package es.deusto.sd.strava.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El ID se genera automáticamente
    private Long id;

    private String username;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserChallenge> challenges = new ArrayList<>();

    private String token; // Token devuelto por Google o Facebook

    private Float weight; // Peso en kg
    private Float height; // Altura en cm
    private Integer maxheartRate; // Máximo ritmo cardíaco en bpm
    private Integer restHeartRate; // Ritmo cardíaco en reposo en bpm

    @ManyToOne
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

    public User(String username, String email, Float weight, Float height, Integer maxheartRate, Integer restHeartRate) {
        this.username = username;
        this.email = email;
        this.token = null;
        this.weight = weight;
        this.height = height;
        this.maxheartRate = maxheartRate;
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

    public Integer getMaxheartRate() {
        return maxheartRate;
    }

    public void setMaxheartRate(Integer maxheartRate) {
        this.maxheartRate = maxheartRate;
    }

    public Integer getRestHeartRate() {
        return restHeartRate;
    }

    public void setRestHeartRate(Integer restHeartRate) {
        this.restHeartRate = restHeartRate;
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
