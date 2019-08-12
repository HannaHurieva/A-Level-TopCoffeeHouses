package com.alevel.project.coffee.service.impl;

import com.alevel.project.coffee.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Can use Spring Security to return currently logged in user
        return Optional.ofNullable(((User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal())
                .getUsername());
    }
}
