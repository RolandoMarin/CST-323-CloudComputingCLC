package com.gcu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gcu.model.Contact;
import com.gcu.model.User;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findByUser(User user);
}