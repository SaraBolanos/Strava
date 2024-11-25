/**
 * This code is based on solutions provided by ChatGPT 4o and 
 * adapted using GitHub Copilot. It has been thoroughly reviewed 
 * and validated to ensure correctness and that it is free of errors.
 */
package es.deusto.sd.googleAuth;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import es.deusto.sd.googleAuth.dao.UserRepository;
import es.deusto.sd.googleAuth.entity.User;

@Configuration
public class DataInitializer {
		
	@Bean
	@Transactional
    CommandLineRunner initData(UserRepository userRepository) {
		return args -> {	
			//Check if database already exists
			if(userRepository.count()>0) {
				return;
			}
			
			// Create some users
			User peter = new User("peter.clement@advance.uk","itEndsToday");
			User julia = new User("julia.salisbury@advance.uk","onlyGettingStarted");
			User theking = new User("kingofallcosmos@katamrai.damacy", "SkyFullOfStars");
			User waymond = new User("waymond.wang@everything.everywhere","allAtOnce");
			User caitlyn = new User("caitlyn@kirammam.plt","AMongoose");// ඞ
			User vi = new User("vi@undercity.zn","OilSlick");
			
			userRepository.saveAll(List.of(peter, julia, theking, waymond, caitlyn, vi));
		};
	}
			
    	
}