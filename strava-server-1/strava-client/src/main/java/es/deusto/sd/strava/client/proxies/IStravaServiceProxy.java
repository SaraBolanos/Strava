package es.deusto.sd.strava.client.proxies;

import java.util.List;

import es.deusto.sd.strava.client.data.Article;
import es.deusto.sd.strava.client.data.Category;
import es.deusto.sd.strava.client.data.Credentials;

public interface IStravaServiceProxy {
	// Method for user login
	String login(Credentials credentials);

	// Method for user logout
	void logout(String token);

	
}
