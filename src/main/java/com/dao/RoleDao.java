package com.dao;

import com.entities.Role;

import java.util.Optional;

public interface RoleDao {

    Optional<Role> findByRoleId(String roleId);
}
