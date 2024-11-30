package com.hari.InternPrep.service;

import com.hari.InternPrep.model.Contact;
import com.hari.InternPrep.repo.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    // Method to save the contact form data
    public String saveContactForm(Contact contact) {
        try {
            // Save the contact form to the database
            contactRepository.save(contact);

            // Return success message
            return "Your message has been successfully sent!";
        } catch (Exception e) {
            // Log the error
            System.out.println("Error saving contact form: " + e.getMessage());
            return "An error occurred while submitting your message. Please try again later.";
        }
    }
}
