package es.deusto.sd.strava.entity;

import jakarta.persistence.*;

@Entity
public class UserChallenge {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    private float progress;  // Current user progress (could be distance or time, depending on challenge)
    private boolean finished;

    // Constructor sin parámetros
    public UserChallenge() {
        super();
    }

    // Constructor con Challenge
    public UserChallenge(Challenge challenge) {
        this.challenge = challenge;
        this.progress = 0.0f;
        this.finished = false;
    }

    // Getters y Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    // hashCode y equals (usados para comparar UserChallenge correctamente en colecciones)
    @Override
    public int hashCode() {
        return 31 * user.hashCode() + challenge.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserChallenge other = (UserChallenge) obj;
        return user.equals(other.user) && challenge.equals(other.challenge);
    }
}
