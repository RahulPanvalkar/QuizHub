package com.controllers;

import com.services.DefaultUserCreation;
import com.utility.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private static final Logger logger = LoggerUtil.getLogger(UserController.class);
    @Autowired
    DefaultUserCreation userCreation;

    // Flag to track if the default users have been added
    private static boolean defaultUsersAdded = false;

    @GetMapping("add-default-users")
    @ResponseBody
    public ResponseEntity<?> addDefaultUsers() {
        logger.info("addDefaultUsers is called...");

        // Check if the default users have already been created
        if (defaultUsersAdded) {
            logger.info("Default users already created.");
            return ResponseEntity.ok("Default users have already been created.");
        }

        // Proceed with user creation logic if not created yet
        ResponseEntity<?> response = userCreation.addDefaultUsers();

        if (response.getStatusCode().is2xxSuccessful()) {
            // Mark as completed after successful creation
            defaultUsersAdded = true;
            logger.info("Default users created successfully.");
        }

        return response;
    }

}
