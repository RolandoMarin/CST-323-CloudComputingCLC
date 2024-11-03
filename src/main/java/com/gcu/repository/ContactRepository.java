package com.gcu.repository;

import com.gcu.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    @Query("SELECT c FROM Contact c JOIN User u ON c.user.id = u.id WHERE u.username = :username")
    List<Contact> findContactsByUsername(String username);
}
