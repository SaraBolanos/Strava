package es.deusto.sd.strava.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.User;


@Repository
public interface UserDAO extends JpaRepository<User, Long>  {
	
	Optional<User> findByEmail(String Email);

	Optional<User> findByUsername(String Username);
	
	User findById(long id);
	
	
}