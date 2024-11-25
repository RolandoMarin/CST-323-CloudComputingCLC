package com.gcu.model;

import jakarta.persistence.*;

@Entity(name = "contacts")  // This annotation defines the class as an entity that will be mapped to a database table named 'contacts'.
public class Contact {

    // The primary key for the contact entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // The ID is auto-generated using the identity strategy (typically for MySQL or similar DBs).
    private Integer id;

    // The contact is associated with a User. This represents a many-to-one relationship.
    @ManyToOne
    @JoinColumn(name = "user_id")  // Defines the foreign key 'user_id' in the 'contacts' table that references the 'users' table.
    private User user;

    // Fields to store contact details
    private String contactName;   // The name of the contact
    private String contactEmail;  // The email of the contact
    private String contactPhone;  // The phone number of the contact
    private String contactAddress; // The address of the contact

    // Getter and setter for 'id' (Primary Key)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter and setter for 'user' (Foreign Key relationship)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getter and setter for 'contactName'
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    // Getter and setter for 'contactEmail'
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    // Getter and setter for 'contactPhone'
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    // Getter and setter for 'contactAddress'
    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }
}
