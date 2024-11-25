package com.gcu.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.model.User;
import com.gcu.service.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String login(Model model, RedirectAttributes redirect, HttpSession httpSession) {
        // Check if the user is already logged in
        User currentUser = (User) httpSession.getAttribute("currentUser");

        if (Objects.nonNull(currentUser)) {
            model.addAttribute("user", currentUser);  // Optionally add the user to the model
            return "redirect:/dashboard";  // Redirect to dashboard if logged in
        }
        model.addAttribute("user", new User());  // Add a blank User object to the model
        return "login";  // Show the login form
    }

    @PostMapping("/")
    public String login(User user, RedirectAttributes redirect, HttpSession httpSession) {
        // Validate the user's credentials
        User validUser = userService.validatUser(user.getEmail(), user.getPassword());

        if (Objects.nonNull(validUser)) {
            // If valid, store the user in the session and redirect to dashboard
            httpSession.setAttribute("currentUser", validUser);
            return "redirect:/dashboard";
        }

        // If invalid, add error message and redirect back to login
        redirect.addFlashAttribute("error", "Invalid email or password, please try again");
        return "redirect:/login/";  // This will re-render the login page with the error message
    }
}
