package com.gcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.model.User;
import com.gcu.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
    
      @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(Model model) {
        return "login"; // Returns the login view
    }

    @PostMapping
    public String login(User user) {
       
        return "redirect:/dashboard"; // Redirect to the dashboard upon successful login
    }

}
