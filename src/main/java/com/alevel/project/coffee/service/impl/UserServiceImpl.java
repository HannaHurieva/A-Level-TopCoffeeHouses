package com.alevel.project.coffee.service.impl;

import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.model.enums.Role;
import com.alevel.project.coffee.model.enums.Status;
import com.alevel.project.coffee.repository.UserRepo;
import com.alevel.project.coffee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.alevel.project.coffee.model.enums.Status.ACTIVE;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(ACTIVE);
        user.setRoles(Collections.singleton(Role.USER));

        userRepo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public boolean isUserExist(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        return userFromDb != null;
    }

    @Override
    public boolean isEmailExist(User user) {
        User userFromDb = userRepo.findByEmail(user.getEmail());
        return userFromDb != null;
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public void updateUserRoleAndStatus(User user, String username, Map<String, String> roles, String status) {
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

    @Override
    public void updateUserProfile(User user, String firstName, String lastName,
                                  String email, String password) {
        if (!(user.getFirstName().equals(firstName))) {
            user.setFirstName(firstName);
        }
        if (!(user.getLastName().equals(lastName))) {
            user.setLastName(lastName);
        }
        if (!StringUtils.isEmpty(email) && !(user.getEmail().equals(email))) {
            user.setEmail(email);
        }
        if (!StringUtils.isEmpty(password) && !(user.getPassword().equals(password))) {
            user.setPassword(passwordEncoder.encode(password));
        }
        userRepo.save(user);
    }
}
