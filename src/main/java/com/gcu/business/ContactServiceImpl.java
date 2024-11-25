package com.gcu.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.gcu.model.Contact;
import com.gcu.model.User;
import com.gcu.repository.ContactRepository;
import com.gcu.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
    private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Autowired
    private ContactRepository contactRepository;

    /**
     * Saves a provided Contact object to the database.
     * 
     * @param contact The Contact object to be saved.
     * @return The saved Contact object with potentially generated ID.
     */
    @Override
    public Contact save(Contact contact) {
        logger.info("Saving contact with details: {}", contact); // Log contact details
        Contact savedContact = contactRepository.save(contact);
        logger.info("Contact saved successfully: {}", savedContact); // Log saved contact details
        return savedContact;
    }

    /**
     * Retrieves all Contact objects associated with a specific User.
     * 
     * @param user The User object whose contacts are to be retrieved.
     * @return A List containing all Contact objects for the provided User.
     */
    @Override
    public List<Contact> getAllContactsForUser(User user) {
        logger.info("Retrieving all contacts for user: {}", user); // Log user details for context
        List<Contact> contacts = contactRepository.findByUser(user);
        logger.info("Retrieved {} contacts for user", contacts.size()); // Log number of retrieved contacts
        return contacts;
    }

    /**
     * Retrieves a Contact object based on its unique identifier.
     * 
     * @param id The ID of the Contact object to be retrieved.
     * @return The Contact object with the matching ID, or null if not found.
     */
    @Override
    public Contact getContactById(Integer id) {
        logger.info("Retrieving contact with ID: {}", id); // Log ID for reference
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact != null) {
            logger.info("Found contact with details: {}", contact); // Log details if found
        } else {
            logger.info("Contact with ID {} not found", id); // Log if not found
        }
        return contact;
    }

    /**
     * Deletes a Contact object from the database based on its ID.
     * 
     * @param id The ID of the Contact object to be deleted.
     */
    @Override
    public void delete(Integer id) {
        logger.info("Deleting contact with ID: {}", id); // Log ID for reference
        contactRepository.deleteById(id);
        logger.info("Contact with ID {} deleted", id); // Log success
    }
}