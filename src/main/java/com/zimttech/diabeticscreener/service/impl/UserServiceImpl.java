package com.zimttech.diabeticscreener.service.impl;

import com.zimttech.diabeticscreener.dto.UserDto;
import com.zimttech.diabeticscreener.entity.User;
import com.zimttech.diabeticscreener.mapper.UserMapper;
import com.zimttech.diabeticscreener.repository.UserRepository;
import com.zimttech.diabeticscreener.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            user.get().getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserMapper.INSTANCE::userToUserDto);
    }

    @Override
    public UserDto getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent())
            return UserMapper.INSTANCE.userToUserDto(userOptional.get());
        throw new UsernameNotFoundException("User Not Found with id : " + id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }

    @Override
    public Optional<User> getOne(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent())
            return userOptional;
        throw new UsernameNotFoundException("User Not Found with id : " + id);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        User existingUser = getOne(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        existingUser = UserMapper.INSTANCE.updateUser(existingUser, user);
        return create(existingUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}
