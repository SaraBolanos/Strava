package es.deusto.sd.strava.external;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component("GoogleAuthGateway")
@Configuration
public class GoogleAuthGateway implements IAuthGateway{
	
	
	private final String API_URL = "/auth/verify";
	
	private final HttpClient httpClient;
    
	
    public GoogleAuthGateway() {
    	this.httpClient = HttpClient.newHttpClient();
    }
	
	@Value("${google.server.url}")
	String serverURL;
	
	@Value("${google.server.port}")
	int serverPort;

	@Override
	public boolean verifyAuth(String email, String password) {
		
		String url = serverURL+ ':' + serverPort + API_URL + "?email=" + email + "&password=" + password;
		
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
