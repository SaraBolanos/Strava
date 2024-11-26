package es.deusto.sd.strava.external;

public interface IFacebookAuthGateway {
	public boolean verifyFacebookAuth(String email, String password);
}
