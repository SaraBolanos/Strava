package es.deusto.sd.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.User;


@Repository
public interface UserDAO extends JpaRepository<User, Long>  {
	
	List<User> FindByEmail(String Email);

	List<User> FindByUsername(String Username);
	
	User findById(long id);
	
	
}