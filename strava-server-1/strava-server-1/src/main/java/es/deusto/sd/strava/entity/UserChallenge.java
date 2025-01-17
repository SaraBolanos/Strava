package es.deusto.sd.strava.entity;

import jakarta.persistence.*;


@Entity
public class UserChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
    @JoinColumn(name = "challenge_id", nullable = false)
    private Challenge challenge;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Reference to the User
    private User user;  // Added User reference

    @Column(nullable = false)
    private float progress; // current user progress (kilometer or time)

    @Column(nullable = false)
    private boolean finished;

    // Constructor without parameters
    public UserChallenge() {}

    // Constructor with parameters
    public UserChallenge(Challenge challenge, User user) {
        this.challenge = challenge;
        this.user = user;
        this.progress = 0.0f;
        this.finished = false;
    }

    public UserChallenge(long id, Challenge challenge, User user) {
        this.id = id;
        this.challenge = challenge;
        this.user = user;
        this.progress = 0.0f;
        this.finished = false;
    }

    // Getters and Setters
    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public User getUser() {
        return user;  // Getter for the User
    }

    public void setUser(User user) {
        this.user = user;  // Setter for the User
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
    
   
}
