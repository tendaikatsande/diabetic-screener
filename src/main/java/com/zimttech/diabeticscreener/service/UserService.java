package com.zimttech.diabeticscreener.service;

import com.zimttech.diabeticscreener.dto.UserDto;
import com.zimttech.diabeticscreener.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface UserService extends UserDetailsService, CrudContract<User> {

    Page<UserDto> getAllUsers(Pageable pageable);

    UserDto getUser(Long id);

    Optional<User> getUserByUsername(String username);

    Optional<User> findByResetToken(String resetToken);
}
