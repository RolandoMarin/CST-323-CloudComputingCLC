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

    @Override
    public User save(User user) {
        logger.info("Entering method save");
        User savedUser = userRepository.save(user);
        logger.info("Exiting method save");
        return savedUser;
    }

    @Override
    public User validatUser(String email, String password) {
        logger.info("Entering method validatUser");
        User user = userRepository.findByEmailAndPassword(email, password);
        logger.info("Exiting method validatUser");
        return user;
    }

    @Override
    public User findByEmail(String email) {
        logger.info("Entering method findByEmail");
        User user = userRepository.findByEmail(email);
        logger.info("Exiting method findByEmail");
        return user;
    }

    @Override
    public User findByUsername(String username) {
        logger.info("Entering method findByUsername");
        User user = userRepository.findByUsername(username);
        logger.info("Exiting method findByUsername");
        return user;
    }
}
