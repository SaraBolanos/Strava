package es.deusto.sd.strava.external;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GoogleAuthGIveaway implements IAuthGateway{
	
	private final String API_URL = "http://localhost:9500/auth/verify";
	
	private final HttpClient httpClient;
    
    public GoogleAuthGIveaway() {
    	this.httpClient = HttpClient.newHttpClient();
    }

	@Override
	public boolean verifyAuth(String email, String password) {
		
		String url = API_URL + "?email=" + email + "&password=" + password;
		
		try {
			HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
			
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			
			if (response.statusCode() == 200) {
				return  true;
			}
			
		} catch (Exception ex) {
        	return false;
        }
		
		return false;
	}

}
