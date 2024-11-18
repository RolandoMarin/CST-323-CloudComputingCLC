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

    @Override
    public Contact save(Contact contact) {
        logger.info("Entering method save");
        Contact savedContact = contactRepository.save(contact);
        logger.info("Exiting method save");
        return savedContact;
    }

    @Override
    public List<Contact> getAllContactsForUser(User user) {
        logger.info("Entering method getAllContactsForUser");
        List<Contact> contacts = contactRepository.findByUser(user);
        logger.info("Exiting method getAllContactsForUser");
        return contacts;
    }

    @Override
    public Contact getContactById(Integer id) {
        logger.info("Entering method getContactById");
        Contact contact = contactRepository.findById(id).orElse(null);
        logger.info("Exiting method getContactById");
        return contact;
    }

    @Override
    public void delete(Integer id) {
        logger.info("Entering method delete");
        contactRepository.deleteById(id);
        logger.info("Exiting method delete");
    }
}
