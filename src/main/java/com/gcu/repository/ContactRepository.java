package com.gcu.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gcu.model.Contact;
import com.gcu.model.User;

// The @Repository annotation is used to indicate that this interface is a Spring Data repository.
// It marks the interface as a bean that Spring will manage and allows for exception translation.
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    // Method to retrieve a list of 'Contact' entities associated with a specific 'User' entity
    // Spring Data JPA will automatically implement this method based on the method signature.
    List<Contact> findByUser(User user);
}
