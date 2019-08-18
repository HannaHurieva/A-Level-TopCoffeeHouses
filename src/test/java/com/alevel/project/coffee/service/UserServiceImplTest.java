package com.alevel.project.coffee.service;

import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.model.enums.Role;
import com.alevel.project.coffee.model.enums.Status;
import com.alevel.project.coffee.repository.UserRepo;
import com.alevel.project.coffee.service.impl.UserServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {
    private UserRepo userRepo = mock(UserRepo.class);
    private PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    private UserServiceImpl userService = new UserServiceImpl(userRepo, passwordEncoder);
    private static final User user = new User();

    @BeforeEach
    void setUp() {
        user.setUsername("User1");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("email@email.email");
        user.setPassword("password");
    }

    @Test
    void shouldCreateNewUserTest() {
        userService.createNewUser(user);
        Assert.assertTrue(CoreMatchers.is(user.getStatus()).matches(Status.ACTIVE));
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    void shouldReturnTrueIfUserExistTest() {
        when(userRepo.findByUsername("User1")).thenReturn(user);
        Assert.assertTrue(userService.isUserExist(user));
    }

    @Test
    void shouldReturnFalseIfUserExistTest() {
        when(userRepo.findByUsername("NotUser")).thenReturn(null);
        Assert.assertFalse(userService.isUserExist(user));
    }

    @Test
    void shouldReturnTrueIfEmailExistTest() {
        when(userRepo.findByEmail("email@email.email")).thenReturn(user);
        Assert.assertTrue(userService.isEmailExist(user));
    }

    @Test
    void shouldReturnFalseIfEmailExistTest() {
        when(userRepo.findByEmail("non@email.email")).thenReturn(null);
        Assert.assertFalse(userService.isEmailExist(user));
    }

    @Test
    void shouldUpdateUserRoleTest() {
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(Role.USER);
        user.setRoles(userRoles);
        user.setId(1l);

        Map<String, String> roles = new LinkedHashMap<>();
        roles.put("username", "User1");
        roles.put("USER", "on");
        roles.put("ADMIN", "on");
        roles.put("userId", "1");

        userService.updateUserRoleAndStatus(user, "User1", roles, String.valueOf(Status.ACTIVE));
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    void shouldUpdateUserStatusToDisabledTest() {
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(Role.USER);
        user.setRoles(userRoles);
        user.setId(1l);

        Map<String, String> roles = new LinkedHashMap<>();
        roles.put("username", "User1");
        roles.put("USER", "on");
        roles.put("userId", "1");

        userService.updateUserRoleAndStatus(user, "User1", roles, String.valueOf(Status.NOT_ACTIVE));
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    void shouldUpdateUserStatusToLockedTest() {
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(Role.USER);
        user.setRoles(userRoles);
        user.setId(1l);

        Map<String, String> roles = new LinkedHashMap<>();
        roles.put("username", "User1");
        roles.put("USER", "on");
        roles.put("userId", "1");

        userService.updateUserRoleAndStatus(user, "User1", roles, String.valueOf(Status.DELETED));
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    void shouldUpdateNameInUserProfileTest() {
        userService.updateUserProfile(user, "Name", "lastName",
                "email@email.email", "password");
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    void shouldUpdateLastNameInUserProfileTest() {
        userService.updateUserProfile(user, "firstName", "Surname",
                "email@email.email", "password");
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    void shouldUpdateNamesToNullInUserProfileTest() {
        userService.updateUserProfile(user, null, null,
                "email@email.email", "password");
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    void shouldUpdatePasswordInUserProfileTest() {
        userService.updateUserProfile(user, "firstName", "lastName",
                "email@email.email", "newPassword");
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    void shouldUpdateEmailInUserProfileTest() {
        userService.updateUserProfile(user, "", "",
                "new.email@email.email", "password");
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }
}
