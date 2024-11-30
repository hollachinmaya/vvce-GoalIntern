package com.hari.InternPrep.controller;


import com.hari.InternPrep.model.User;
import com.hari.InternPrep.service.JwtService;
import com.hari.InternPrep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            // Save the user
            User savedUser = service.saveUser(user);

            // Generate JWT token for the new user
            String token = jwtService.generateToken(savedUser.getUsername());

            // Return the JWT token
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            // Check if authentication is successful
            if (authentication.isAuthenticated()) {
                // Extract the authenticated username
                String authenticatedUsername = authentication.getName();

                // Generate JWT token
                String token = jwtService.generateToken(authenticatedUsername);

                // Return the JWT token
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + e.getMessage());
        }
    }
}
