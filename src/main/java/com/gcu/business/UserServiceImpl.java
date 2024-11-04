package com.gcu.business;

import com.gcu.model.User;
import com.gcu.repository.UserRepository;
import com.gcu.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserRepository userRepository;

@Override
public User save(User user) {
    return userRepository.save(user);
}

@Override
public User validatUser(String email, String password) {
   return userRepository.findByEmailAndPassword(email, password);
}
@Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

@Override
public User findByUsername(String username) {
  
   return userRepository.findByUsername(username);
}
}
