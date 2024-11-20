package com.dao;

import com.entities.User;

import java.util.Optional;

public interface UserDao {

    User saveUser(User user);

    public Optional<User> findByUsername(String username);

    Optional<User> findByEmailId(String emailId);

    User getUserById(Long id);

    void updateUser(User user);

    void deleteUser(User user);
}
