package es.deusto.sd.strava.external;

public interface IGoogleAuthGateway {
	public boolean verifyGoogleAuth(String email, String password);
}
