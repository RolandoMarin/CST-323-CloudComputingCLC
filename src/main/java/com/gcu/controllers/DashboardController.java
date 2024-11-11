package com.gcu.controllers;

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

    @Autowired
    private ContactService contactService;

    @GetMapping("")
    public String getContacts(Model model, HttpSession httpSession) {
        User currentUser = checkSession(httpSession);
        if (currentUser == null) return "redirect:/login/";

        model.addAttribute("contacts", contactService.getAllContactsForUser(currentUser));
        return "dashboard";
    }

    @GetMapping("/create")
    public String createContactForm(Model model, HttpSession httpSession) {
        if (checkSession(httpSession) == null) return "redirect:/login/";

        model.addAttribute("contact", new Contact());
        return "createcontact";
    }

    @PostMapping("/create")
    public String createContact(Contact contact, HttpSession httpSession, RedirectAttributes redirect) {
        User currentUser = checkSession(httpSession);
        if (currentUser == null) return "redirect:/login/";

        contact.setUser(currentUser);
        contactService.save(contact);
        redirect.addFlashAttribute("success", "Contact created successfully");
        return "redirect:/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editContactForm(@PathVariable("id") Integer id, Model model, HttpSession httpSession) {
        if (checkSession(httpSession) == null) return "redirect:/login/";

        model.addAttribute("contact", contactService.getContactById(id));
        return "editcontact";
    }

    @PostMapping("/edit/{id}")
    public String updateContact(@PathVariable("id") Integer id, Contact contact, HttpSession httpSession, RedirectAttributes redirect) {
        User currentUser = checkSession(httpSession);
        if (currentUser == null) return "redirect:/login/";
    
      
        Contact existingContact = contactService.getContactById(id);
        if (existingContact != null) {
            // Update only the editable fields
            contact.setId(id);
            contact.setUser(existingContact.getUser());
            contactService.save(contact);
            redirect.addFlashAttribute("success", "Contact updated successfully");
        } else {
            redirect.addFlashAttribute("error", "Contact not found");
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String deleteContact(@PathVariable("id") Integer id, RedirectAttributes redirect) {
        contactService.delete(id);
        redirect.addFlashAttribute("success", "Contact deleted successfully");
        return "redirect:/dashboard";
    }

    private User checkSession(HttpSession httpSession) {
        return (User) httpSession.getAttribute("currentUser");
    }
}
