package es.deusto.sd.strava.external;

public interface IAuthGateway {
	public boolean verifyAuth(String email, String password);
}
