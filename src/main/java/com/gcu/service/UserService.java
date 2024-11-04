package com.gcu.service;

import com.gcu.model.User;

public interface UserService {
     User Save(User user);
     User validatUser(String email, String password);
}