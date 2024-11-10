package es.deusto.sd.strava.entity;

public class Session {
	String sessionToken;
	String userToken;
	
	public Session(String sessionToken, String userToken) {
		super();
		this.sessionToken = sessionToken;
		this.userToken = userToken;
	}
	
}
