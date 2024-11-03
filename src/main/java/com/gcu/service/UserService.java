package com.gcu.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.model.User;
import com.gcu.repository.UserRepository;

@Service
public class UserService {
     @Autowired
    private UserRepository userRepository; // Inject your UserRepository

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username); // Implement this in your UserRepository
    }

    public void save(User user) {
        userRepository.save(user); // Save the user to the database
    }

    public boolean checkUserExists(String username) {
        return userRepository.existsByUsername(username); // Implement this in your UserRepository
    }
}
