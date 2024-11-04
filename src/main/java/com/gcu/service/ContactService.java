package com.gcu.service;

import java.util.List;

import com.gcu.model.Contact;
import com.gcu.model.User;

public interface ContactService {
    Contact save(Contact contact);
    List<Contact> getAllContactsForUser(User user);
    Contact getContactById(Integer id);      
    void delete(Integer id);                 
}
