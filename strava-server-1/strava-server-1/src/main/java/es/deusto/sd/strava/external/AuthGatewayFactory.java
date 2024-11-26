package es.deusto.sd.strava.external;

public class AuthGatewayFactory {
	public static IAuthGateway createAuthGateway(String accountType) {
		if(accountType.equals("Google")) {
			return new GoogleAuthGIveaway();
		}else {
			return new FacebookAuthGateway();
		}
	}
	
	
}
