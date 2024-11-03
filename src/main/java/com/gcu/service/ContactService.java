package com.gcu.service;

import com.gcu.model.Contact;
import com.gcu.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getContactsByUsername(String username) {
        return contactRepository.findContactsByUsername(username);
    }
}
