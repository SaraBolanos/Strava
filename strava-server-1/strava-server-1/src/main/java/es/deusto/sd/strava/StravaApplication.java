/**
 * This code is based on solutions provided by ChatGPT 4o and 
 * adapted using GitHub Copilot. It has been thoroughly reviewed 
 * and validated to ensure correctness and that it is free of errors.
 */
package es.deusto.sd.strava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "es.deusto.sd.strava.repository")  // Asegúrate de que este paquete esté correctamente configurado
@EntityScan(basePackages = "es.deusto.sd.strava.entity")  
public class StravaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StravaApplication.class, args);
	}
}
