package es.deusto.sd.strava.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find users by email
    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    // Find users by username
    @Query(value = "SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);

    // Find user by id (already provided by JpaRepository)
    User findById(long id);
    
    @Query("SELECT u FROM User u WHERE u.token = :token")
    User findByToken(@Param("token") String token);
    
}
