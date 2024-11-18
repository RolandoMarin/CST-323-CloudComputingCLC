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
    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private ContactService contactService;

    @GetMapping("")
    public String getContacts(Model model, HttpSession httpSession) {
        logger.info("Entering method getContacts");
        User currentUser = checkSession(httpSession);
        if (currentUser == null) return "redirect:/login/";

        model.addAttribute("contacts", contactService.getAllContactsForUser(currentUser));
        logger.info("Exiting method getContacts");
        return "dashboard";
    }

    @GetMapping("/create")
    public String createContactForm(Model model, HttpSession httpSession) {
        logger.info("Entering method createContactForm");
        if (checkSession(httpSession) == null) return "redirect:/login/";

        model.addAttribute("contact", new Contact());
        logger.info("Exiting method createContactForm");
        return "createcontact";
    }

    @PostMapping("/create")
    public String createContact(Contact contact, HttpSession httpSession, RedirectAttributes redirect) {
        logger.info("Entering method createContact");
        User currentUser = checkSession(httpSession);
        if (currentUser == null) return "redirect:/login/";

        contact.setUser(currentUser);
        contactService.save(contact);
        redirect.addFlashAttribute("success", "Contact created successfully");
        logger.info("Exiting method createContact");
        return "redirect:/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editContactForm(@PathVariable("id") Integer id, Model model, HttpSession httpSession) {
        logger.info("Entering method editContactForm");
        if (checkSession(httpSession) == null) return "redirect:/login/";

        model.addAttribute("contact", contactService.getContactById(id));
        logger.info("Exiting method editContactForm");
        return "editcontact";
    }

    @PostMapping("/edit/{id}")
    public String editContact(@PathVariable("id") Integer id, Contact contact, HttpSession httpSession, RedirectAttributes redirect) {
        logger.info("Entering method editContact");
        User currentUser = checkSession(httpSession);
        if (currentUser == null) return "redirect:/login/";

        Contact existingContact = contactService.getContactById(id);
        if (existingContact != null) {
            contact.setId(id);
            contact.setUser(existingContact.getUser());
            contactService.save(contact);
            redirect.addFlashAttribute("success", "Contact updated successfully");
        } else {
            redirect.addFlashAttribute("error", "Contact not found");
        }
        logger.info("Exiting method editContact");
        return "redirect:/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String deleteContact(@PathVariable("id") Integer id, RedirectAttributes redirect) {
        logger.info("Entering method deleteContact");
        contactService.delete(id);
        redirect.addFlashAttribute("success", "Contact deleted successfully");
        logger.info("Exiting method deleteContact");
        return "redirect:/dashboard";
    }

    private User checkSession(HttpSession httpSession) {
        return (User) httpSession.getAttribute("currentUser");
    }
}
