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

    // Auto-wiring the UserService to handle user-related business logic (like saving and checking existing users)
    @Autowired
    private UserService userService;

    /**
     * Method to show the registration form (GET request).
     * @param model The model to pass data to the view (e.g., an empty User object).
     * @return The view name for the registration page.
     */
    @GetMapping("/")
    public String showRegistrationForm(Model model) {
        // Add an empty User object to the model to pre-populate the registration form
        model.addAttribute("user", new User());
        return "register";  // Return the view name for the registration form
    }

    /**
     * Method to handle user registration (POST request).
     * Validates if the email or username already exists, then registers the user.
     * @param user The user object containing the registration data (email, username, password, etc.).
     * @param redirect RedirectAttributes to pass any flash attributes (e.g., error or success messages).
     * @return A redirect to the appropriate page (back to registration with error or to login with success).
     */
    @PostMapping("/")
    public String registerUser(User user, RedirectAttributes redirect) {
        // Check if the email already exists in the database
        if (userService.findByEmail(user.getEmail()) != null) {
            // If email is already registered, add an error message and redirect back to the registration form
            redirect.addFlashAttribute("error", "Email already exists");
            return "redirect:/register/";  // Redirect to the registration page to fix the issue
        }

        // Check if the username already exists in the database
        if (userService.findByUsername(user.getUsername()) != null) {
            // If username is already taken, add an error message and redirect back to the registration form
            redirect.addFlashAttribute("error", "Username already exists");
            return "redirect:/register/";  // Redirect to the registration page to fix the issue
        }

        // If both email and username are available, proceed to save the new user in the database
        userService.save(user);

        // Add a success message to inform the user that the registration was successful
        redirect.addFlashAttribute("success", "Registration successful! Please login.");

        // Redirect to the login page after successful registration
        return "redirect:/login/";  // Redirect to the login page to allow the user to log in
    }
}
