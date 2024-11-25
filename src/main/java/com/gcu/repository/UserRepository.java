package com.gcu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gcu.model.User;

// The @Repository annotation marks this interface as a Spring Data repository.
// It indicates that this interface will be used for data access operations related to the 'User' entity.
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query method to find a User by their email and password.
    // Spring Data JPA will automatically implement this method based on the method signature.
    User findByEmailAndPassword(String email, String password);

    // Custom query method to find a User by their email.
    // Spring Data JPA will generate the query automatically based on the method name.
    User findByEmail(String email);

    // Custom query method to find a User by their username.
    // Similar to the other methods, Spring Data JPA will handle the implementation.
    User findByUsername(String username);
}
