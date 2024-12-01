package es.deusto.sd.strava.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find users by email
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(@Param("email") String email);

    // Find users by username
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);

    // Find user by id (already provided by JpaRepository)
    User findById(long id);
    
    @Query("SELECT u FROM User u WHERE u.token = :token")
    User findByToken(@Param("token") String token);
    
    /*@Query("INSERT INTO User (username,email,token,weight,height,maxHeartRate,restHeartRate) VALUES (:username,:email,null,:weight,:height,:maxHeartRate,:restHeartRate)")
    void addUser(@Param("username") String username,
    			 @Param("email") String email,
    			 @Param("weight") float weight,
    			 @Param("height") float height,
    			 @Param("maxHeartRate") int maxHeartRate,
    			 @Param("restHeartRate") int restHeartRate);*/
}
