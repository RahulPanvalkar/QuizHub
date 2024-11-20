package com.services.impl;

import com.dao.RoleDao;
import com.dao.UserDao;
import com.entities.Role;
import com.entities.User;
import com.services.UserService;
import com.services.impl.DefaultUserCreation;
import com.utility.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private BCryptPasswordEncoder passwordEncoder;
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    private static final Logger logger = LoggerUtil.getLogger(DefaultUserCreation.class);

    // Constructor Injection
    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Map<String, Object> createUser(User user) {
        logger.info("Inside createUser >> [{}]", user);
        Map<String, Object> retMap = new HashMap<>();

        // Check if user already exists by username or email
        if (userDao.findByUsername(user.getUsername()).isPresent() ||
                userDao.findByEmailId(user.getEmailId()).isPresent()) {
            logger.info("User already present: [{}]", user.getUsername());
            retMap.put("error", true);
            retMap.put("message", "User already present");
            return retMap;
        }

        String password = user.getPassword();
        logger.info("password :: [{}]", password);
        String encodedPassword = "";
        // Setting default password for user
        if (password == null || password.isBlank()){
            String chars = user.getFirstName().trim().toLowerCase().substring(0,4);
            String numbers = user.getPhone().trim().substring(6);
            password = chars+"@"+numbers;
            logger.info("updatedPassword :: [{}]", password);
        }
        user.setPassword(passwordEncoder.encode(password));

        // setting user roles
        Set<Role> roles = new HashSet<>();
        Role userRole = roleDao.findByRoleId("NORM").orElse(new Role("NORM","NORMAL"));
        roles.add(userRole);
        user.setRoles(roles);

        String phone = user.getPhone();
        if (phone != null && (phone.trim().length() != 10 || phone.trim().matches("^[6-9]\\d{9}$"))){
            retMap.put("error", true);
            retMap.put("message", "Invalid mobile Number");
            return retMap;
        }

        try {
            // Save the user
            User savedUser = userDao.saveUser(user);
            logger.info("User created by username: [{}]", savedUser.getUsername());
            retMap.put("message","User has been created");
            retMap.put("error", false);
            retMap.put("user", savedUser);

        } catch (Exception e) {
            logger.error("Error creating user", e);
            retMap.put("error", true);
            retMap.put("message", e);
        }
        return retMap;
    }
}
