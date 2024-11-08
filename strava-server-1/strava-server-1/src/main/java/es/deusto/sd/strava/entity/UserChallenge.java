package es.deusto.sd.strava.entity;

public class UserChallenge {
	
	private Challenge challenge;
	private float progress; //current user progress (puede ser kilometro o tiempo -depents on targettype of challenge)
	//private float porcentage;  //how much of the goal is achieved (is redundant because can be calculated at other points)
	private boolean finished;
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
	public UserChallenge(Challenge challenge) {
		super();
		this.challenge = challenge;
		this.progress = 0.0f;
		this.finished = false;
	}
	
	
	
}
