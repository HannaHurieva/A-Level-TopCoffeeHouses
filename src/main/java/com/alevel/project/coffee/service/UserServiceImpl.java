package com.alevel.project.coffee.service;

import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.model.enums.Role;
import com.alevel.project.coffee.model.enums.Status;
import com.alevel.project.coffee.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> roles, String status) {
        user.setUsername(username);

        Set<String> rolesSet = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();
        for (String key : roles.keySet()) {
            if (rolesSet.contains(key))
                user.getRoles().add(Role.valueOf(key));
        }

        user.setStatus(Status.valueOf(status));
        userRepo.save(user);
}

    public void updateProfile(User user, String firstName, String lastName,
                              String email, String password) {
        if (!StringUtils.isEmpty(firstName) && !(user.getFirstName().equals(firstName))) {
            user.setFirstName(firstName);
        }
        if (!StringUtils.isEmpty(lastName) && !(user.getLastName().equals(lastName))) {
            user.setLastName(lastName);
        }
        if (!StringUtils.isEmpty(email) && !(user.getEmail().equals(email))){
            user.setEmail(email);
        }
        if (!StringUtils.isEmpty(password) && !(user.getPassword().equals(password))) {
            user.setPassword(passwordEncoder.encode(password));
        }
        userRepo.save(user);
    }
}