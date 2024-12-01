package es.deusto.sd.strava.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.deusto.sd.strava.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find users by email
    @Query(value = "SELECT u FROM User u WHERE u.email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);

    // Find users by username
    @Query(value = "SELECT u FROM User u WHERE u.username = :username", nativeQuery = true)
    User findByUsername(@Param("username") String username);

    // Find user by id (already provided by JpaRepository)
    User findById(long id);
}
