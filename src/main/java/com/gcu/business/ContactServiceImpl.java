package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.model.Contact;
import com.gcu.model.User;
import com.gcu.repository.ContactRepository;
import com.gcu.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAllContactsForUser(User user) {
        return contactRepository.findByUser(user);
    }

    @Override
    public Contact getContactById(Integer id) {
        // TODO Auto-generated method stub
        return contactRepository.findById(id).orElse(null); 
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        contactRepository.deleteById(id); // Deletes a contact by ID
    }
}
