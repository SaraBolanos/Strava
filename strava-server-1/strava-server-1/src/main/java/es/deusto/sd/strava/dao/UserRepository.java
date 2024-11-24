package es.deusto.sd.strava.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.entity.UserChallenge;
import es.deusto.sd.strava.enums.Sport;


@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
	
	//List<User> FindByEmail(String Email);

	//List<User> FindByUsername(String Username);
	
	User findById(long id);
	


 
	
}