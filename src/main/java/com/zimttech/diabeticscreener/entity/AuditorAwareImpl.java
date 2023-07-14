package com.zimttech.diabeticscreener.entity;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Can use Spring Security to return currently logged in user
//        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
//            return Optional.ofNullable(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        return Optional.of("system");
    }
}
