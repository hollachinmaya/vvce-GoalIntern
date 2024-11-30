package com.hari.InternPrep.controller;

import com.hari.InternPrep.model.Contact;
import com.hari.InternPrep.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {
    @Autowired
    private ContactService service;


        @PostMapping("/contact")
        public ResponseEntity<String> submitContactForm(@RequestBody Contact contact) {
            // Call service to save the contact form data
            String responseMessage = service.saveContactForm(contact);

            // Return success or failure response
            return ResponseEntity.ok(responseMessage);
        }

}
