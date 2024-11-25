package es.deusto.sd.strava.entity;

import java.time.LocalDate;
import java.util.Objects;

import es.deusto.sd.strava.enums.Sport;
import es.deusto.sd.strava.enums.TargetType;
import jakarta.persistence.*;

@Entity
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El ID se genera automáticamente
    private Long id;

    private String name;

    private LocalDate startDate; // Usamos LocalDate para representar fechas
    private LocalDate endDate;

    private float target;

    @Enumerated(EnumType.STRING)
    private TargetType targetType;

    @Enumerated(EnumType.STRING)
    private Sport sport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    // Constructor sin parámetros
    public Challenge() {}

    // Constructor con parámetros
    public Challenge(String name, LocalDate startDate, LocalDate endDate, float target, TargetType targetType, Sport sport, User creator) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.target = target;
        this.targetType = targetType;
        this.sport = sport;
        this.creator = creator;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public float getTarget() {
        return target;
    }

    public void setTarget(float target) {
        this.target = target;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(creator, endDate, name, sport, startDate, target, targetType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Challenge other = (Challenge) obj;
        return Objects.equals(creator, other.creator) && Objects.equals(endDate, other.endDate)
                && Objects.equals(name, other.name) && sport == other.sport
                && Objects.equals(startDate, other.startDate)
                && Float.floatToIntBits(target) == Float.floatToIntBits(other.target) && targetType == other.targetType;
    }

    // Método para verificar si el desafío ha terminado
    public boolean isFinished() {
        return endDate.isBefore(LocalDate.now());
    }
}
