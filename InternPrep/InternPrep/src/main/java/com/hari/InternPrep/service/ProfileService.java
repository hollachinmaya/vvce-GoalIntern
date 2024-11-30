package com.hari.InternPrep.service;

import com.hari.InternPrep.model.Profile;
import com.hari.InternPrep.model.User;
import com.hari.InternPrep.repo.ProfileRepository;
import com.hari.InternPrep.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    // Get profile by User ID
    public Profile getProfileByUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        return profileRepository.findByUser(user.get());
    }

    // Create or update profile for a User
    public Profile saveOrUpdateProfile(Long userId, Profile profile) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        // Link profile to the user
        profile.setUser(user.get());
        return profileRepository.save(profile);
    }
}
