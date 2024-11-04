// RegistrationController.java
package com.gcu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.model.User;
import com.gcu.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/")
    public String registerUser(User user, RedirectAttributes redirect) {
        // Check if email already exists
        if (userService.findByEmail(user.getEmail()) != null) {
            redirect.addFlashAttribute("error", "Email already exists");
            return "redirect:/register/";
        }
        
        // Check if username already exists
        if (userService.findByUsername(user.getUsername()) != null) {
            redirect.addFlashAttribute("error", "Username already exists");
            return "redirect:/register/";
        }

        userService.save(user);
        redirect.addFlashAttribute("success", "Registration successful! Please login.");
        return "redirect:/login/";
    }
}