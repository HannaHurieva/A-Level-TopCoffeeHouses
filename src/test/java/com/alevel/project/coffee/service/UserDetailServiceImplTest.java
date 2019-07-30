package com.alevel.project.coffee.service;

import com.alevel.project.coffee.repository.UserRepo;
import com.alevel.project.coffee.service.impl.UserDetailServiceImpl;
import org.junit.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDetailServiceImplTest {
    private UserRepo userRepo = mock(UserRepo.class);

    private UserDetailServiceImpl userService = new UserDetailServiceImpl(userRepo);

    @Test
    public void shouldThrowUsernameNotFoundException() throws UsernameNotFoundException {
        when(userRepo.findByUsername("NotUser")).thenReturn(null);
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("NotUser"));
    }
}
