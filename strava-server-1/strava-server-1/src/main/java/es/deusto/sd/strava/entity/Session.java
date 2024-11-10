package es.deusto.sd.strava.entity;

public class Session {
	String sessionToken;
	String userToken;
	
	public Session(String sessionToken, String userToken) {
		super();
		this.sessionToken = sessionToken;
		this.userToken = userToken;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
	
	
}
