package com.gcu.service;

import com.gcu.model.User;

public interface UserService {
     User save(User user);
     User findByEmail(String email);
     User findByUsername(String username);
     User validatUser(String email, String password);
}