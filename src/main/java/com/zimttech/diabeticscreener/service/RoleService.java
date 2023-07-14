package com.zimttech.diabeticscreener.service;


import com.zimttech.diabeticscreener.entity.Role;

import java.util.List;

public interface RoleService extends CrudContract<Role> {
    List<Role> createMultiple(List<Role> roles);
}
