package com.hari.InternPrep.repo;

import com.hari.InternPrep.model.Profile;
import com.hari.InternPrep.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUser(User user);
}
