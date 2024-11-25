package com.gcu.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.model.User;
import com.gcu.repository.UserRepository;
import com.gcu.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

      /**
     * Saves a new user to the database.
     *
     * @param user The user object to be saved.
     * @return The saved user object.
     */
    @Override
    public User save(User user) {
        logger.info("Entering method save");
        User savedUser = userRepository.save(user);
        logger.info("Exiting method save");
        return savedUser;
    }
    /**
     * Validates a user based on their email and password.
     *
     * @param email The user's email address.
     * @param password The user's password.
     * @return The validated user, or null if not found.
     */
    @Override
    public User validatUser(String email, String password) {
        logger.info("Entering method validatUser");
        User user = userRepository.findByEmailAndPassword(email, password);
        logger.info("Exiting method validatUser");
        return user;
    }

      /**
     * Finds a user by their email address.
     *
     * @param email The user's email address.
     * @return The found user, or null if not found.
     */
    @Override
    public User findByEmail(String email) {
        logger.info("Entering method findByEmail");
        User user = userRepository.findByEmail(email);
        logger.info("Exiting method findByEmail");
        return user;
    }

     /**
     * Finds a user by their username.
     *
     * @param username The user's username.
     * @return The found user, or null if not found.
     */
    @Override
    public User findByUsername(String username) {
        logger.info("Entering method findByUsername");
        User user = userRepository.findByUsername(username);
        logger.info("Exiting method findByUsername");
        return user;
    }
}
