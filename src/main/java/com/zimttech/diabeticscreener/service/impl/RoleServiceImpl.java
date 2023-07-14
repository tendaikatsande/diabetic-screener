package com.zimttech.diabeticscreener.service.impl;

import com.zimttech.diabeticscreener.entity.Role;
import com.zimttech.diabeticscreener.repository.RoleRepository;
import com.zimttech.diabeticscreener.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Page<Role> getAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public Optional<Role> getOne(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent())
            return roleOptional;
        throw new NotFoundException("Role Not Found with Id : " + id);
    }

    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role update(Long id, Role role) {
        Optional<Role> roleOptional = getOne(id);
        return create(role);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> createMultiple(List<Role> roles) {
        return roleRepository.saveAll(roles);
    }
}
