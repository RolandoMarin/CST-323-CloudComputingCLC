package com.gcu.model;

import jakarta.persistence.*;
import java.util.List;

// Entity annotation tells JPA that this class will be mapped to a database table
@Entity(name = "users")
public class User {

    // 'Id' annotation marks the 'id' field as the primary key for the 'users' table
    @Id
    // 'GeneratedValue' annotation specifies how the primary key value is generated (e.g., auto-incrementing)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Fields representing the user's data: username, password, email, first name, and last name
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    // 'OneToMany' annotation indicates a one-to-many relationship with the 'Contact' entity.
    // 'mappedBy' specifies that the 'user' field in the 'Contact' class owns the relationship (i.e., it's the owning side).
    // 'CascadeType.ALL' means that any operations (like persist, update, delete) on this User will cascade to the associated Contacts.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Contact> contacts;

    // Getter and setter methods for the 'id' field
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter and setter methods for the 'username' field
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and setter methods for the 'password' field
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and setter methods for the 'email' field
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and setter methods for the 'firstName' field
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter and setter methods for the 'lastName' field
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter and setter methods for the 'contacts' list, which represents the user's contacts
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
