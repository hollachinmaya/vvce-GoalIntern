package com.hari.InternPrep.controller;

import com.hari.InternPrep.model.Profile;
import com.hari.InternPrep.service.ProfileService;
import com.hari.InternPrep.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "http://localhost:5173")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private JwtService jwtService;

    // Fetch profile by user ID
    @GetMapping("/{userId}")
    public ResponseEntity<?> getProfileByUserId(@PathVariable Long userId) {
        try {
            Profile profile = profileService.getProfileByUserId(userId);

            // Generate JWT token
            String jwtToken = jwtService.generateToken(profile.getUsername());

            return ResponseEntity.ok(Map.of(
                    "profile", profile,
                    "jwtToken", jwtToken
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of(
                    "error", e.getMessage()
            ));
        }
    }

    // Create or update profile
    @PostMapping("/{userId}")
    public ResponseEntity<?> saveOrUpdateProfile(@PathVariable Long userId, @RequestBody Profile profile) {
        try {
            Profile updatedProfile = profileService.saveOrUpdateProfile(userId, profile);

            // Generate JWT token
            String jwtToken = jwtService.generateToken(updatedProfile.getUsername());

            return ResponseEntity.ok(Map.of(
                    "profile", updatedProfile,
                    "jwtToken", jwtToken
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of(
                    "error", e.getMessage()
            ));
        }
    }
}
