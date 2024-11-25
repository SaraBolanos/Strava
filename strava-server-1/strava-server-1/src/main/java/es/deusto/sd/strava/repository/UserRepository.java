package es.deusto.sd.strava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
	
	
	
	List<User> findByEmail(String Email);

	List<User> findByUsername(String Username);
	
	User findById(long id);
	
	
}