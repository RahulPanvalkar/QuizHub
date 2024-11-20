package com.services;

import com.entities.User;

import java.util.Map;

public interface UserService {

    Map<String, Object>  createUser(User user);
}
