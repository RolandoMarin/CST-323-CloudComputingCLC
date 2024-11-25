package com.gcu.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.model.Contact;
import com.gcu.model.User;
import com.gcu.service.ContactService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    
    // Logger instance to log important information during method execution
    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    // Auto-wiring ContactService to handle business logic related to contacts
    @Autowired
    private ContactService contactService;

    /**
     * Method to handle GET requests for fetching all contacts of the current user
     * @param model The model to pass data to the view
     * @param httpSession The HTTP session to check the logged-in user
     * @return The view name "dashboard" with all contacts
     */
    @GetMapping("")
    public String getContacts(Model model, HttpSession httpSession) {
        logger.info("Entering method getContacts");

        // Check if the user is logged in by verifying the session
        User currentUser = checkSession(httpSession);
        if (currentUser == null) return "redirect:/login/";

        // Retrieve and add the user's contacts to the model
        model.addAttribute("contacts", contactService.getAllContactsForUser(currentUser));
        logger.info("Exiting method getContacts");

        // Return the dashboard view displaying the contacts
        return "dashboard";
    }

    /**
     * Method to show the contact creation form
     * @param model The model to pass a blank contact object to the view
     * @param httpSession The HTTP session to check the logged-in user
     * @return The view name "createcontact" to show the creation form
     */
    @GetMapping("/create")
    public String createContactForm(Model model, HttpSession httpSession) {
        logger.info("Entering method createContactForm");

        // Check if the user is logged in by verifying the session
        if (checkSession(httpSession) == null) return "redirect:/login/";

        // Add a new blank Contact object to the model to pre-populate the form
        model.addAttribute("contact", new Contact());
        logger.info("Exiting method createContactForm");

        // Return the form view to create a new contact
        return "createcontact";
    }

    /**
     * Method to handle form submission for creating a new contact
     * @param contact The contact data submitted by the user
     * @param httpSession The HTTP session to check the logged-in user
     * @param redirect RedirectAttributes to pass flash attributes (e.g., success message)
     * @return The redirect view to the dashboard with a success message
     */
    @PostMapping("/create")
    public String createContact(Contact contact, HttpSession httpSession, RedirectAttributes redirect) {
        logger.info("Entering method createContact");

        // Check if the user is logged in by verifying the session
        User currentUser = checkSession(httpSession);
        if (currentUser == null) return "redirect:/login/";

        // Associate the contact with the current logged-in user
        contact.setUser(currentUser);
        
        // Save the contact to the database
        contactService.save(contact);
        
        // Add a success message to the redirect attributes
        redirect.addFlashAttribute("success", "Contact created successfully");

        logger.info("Exiting method createContact");

        // Redirect to the dashboard after creating the contact
        return "redirect:/dashboard";
    }

    /**
     * Method to show the form for editing an existing contact
     * @param id The ID of the contact to edit
     * @param model The model to pass the contact data to the view
     * @param httpSession The HTTP session to check the logged-in user
     * @return The view name "editcontact" to show the contact editing form
     */
    @GetMapping("/edit/{id}")
    public String editContactForm(@PathVariable("id") Integer id, Model model, HttpSession httpSession) {
        logger.info("Entering method editContactForm");

        // Check if the user is logged in by verifying the session
        if (checkSession(httpSession) == null) return "redirect:/login/";

        // Retrieve the contact by ID and add it to the model
        model.addAttribute("contact", contactService.getContactById(id));

        logger.info("Exiting method editContactForm");

        // Return the form view to edit the contact
        return "editcontact";
    }

    /**
     * Method to handle form submission for editing an existing contact
     * @param id The ID of the contact to update
     * @param contact The updated contact data
     * @param httpSession The HTTP session to check the logged-in user
     * @param redirect RedirectAttributes to pass flash attributes (e.g., success/error message)
     * @return The redirect view to the dashboard with a success or error message
     */
    @PostMapping("/edit/{id}")
    public String editContact(@PathVariable("id") Integer id, Contact contact, HttpSession httpSession, RedirectAttributes redirect) {
        logger.info("Entering method editContact");

        // Check if the user is logged in by verifying the session
        User currentUser = checkSession(httpSession);
        if (currentUser == null) return "redirect:/login/";

        // Retrieve the existing contact by ID
        Contact existingContact = contactService.getContactById(id);
        if (existingContact != null) {
            // Update the existing contact with the new values
            contact.setId(id);
            contact.setUser(existingContact.getUser());
            
            // Save the updated contact to the database
            contactService.save(contact);

            // Add a success message to the redirect attributes
            redirect.addFlashAttribute("success", "Contact updated successfully");
        } else {
            // If the contact doesn't exist, add an error message
            redirect.addFlashAttribute("error", "Contact not found");
        }

        logger.info("Exiting method editContact");

        // Redirect to the dashboard after editing the contact
        return "redirect:/dashboard";
    }

    /**
     * Method to handle deleting a contact
     * @param id The ID of the contact to delete
     * @param redirect RedirectAttributes to pass flash attributes (e.g., success message)
     * @return The redirect view to the dashboard with a success message
     */
    @PostMapping("/delete/{id}")
    public String deleteContact(@PathVariable("id") Integer id, RedirectAttributes redirect) {
        logger.info("Entering method deleteContact");

        // Delete the contact by ID
        contactService.delete(id);
        
        // Add a success message to the redirect attributes
        redirect.addFlashAttribute("success", "Contact deleted successfully");

        logger.info("Exiting method deleteContact");

        // Redirect to the dashboard after deleting the contact
        return "redirect:/dashboard";
    }

    /**
     * Helper method to check if the current user is logged in by inspecting the session
     * @param httpSession The HTTP session containing the user data
     * @return The current User object if logged in, null if not logged in
     */
    private User checkSession(HttpSession httpSession) {
        return (User) httpSession.getAttribute("currentUser");
    }
}
