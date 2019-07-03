package com.alevel.project.coffee.service;

import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.model.enums.Role;
import com.alevel.project.coffee.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.alevel.project.coffee.model.enums.Status.ACTIVE;

@Service
public class UserServiceImpl {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean addNewUser(User user) {
        User userByUsername = userRepo.findByUsername(user.getUsername());
        User userByEmail = userRepo.findByEmail(user.getEmail());
        if (userByUsername != null || userByEmail != null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(ACTIVE);
        user.setRoles(Collections.singleton(Role.USER));

        userRepo.save(user);
        return true;
    }
}
