package com.gcu.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gcu.model.Contact;
import com.gcu.service.ContactService;

@Controller
public class DashboardController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model, Principal principal) {
        // Get the logged-in username
        String username = principal.getName();
        
        // Retrieve contacts for the logged-in user
        List<Contact> contacts = contactService.getContactsByUsername(username);
        
        model.addAttribute("contacts", contacts);
        
        return "dashboard";
    }
}
