package com.controllers;

import com.entities.User;
import com.services.impl.DefaultUserCreation;
import com.services.UserService;
import com.utility.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api")
public class APIController {

    private static final Logger logger = LoggerUtil.getLogger(APIController.class);
    @Autowired
    DefaultUserCreation userCreation;
    @Autowired
    UserService userService;

    // Flag to track if the default users have been added
    private static boolean defaultUsersAdded = false;

    @GetMapping("/add-default-users")
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


    @PostMapping(value = "/add-user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestBody User user) {
        logger.info("Inside addUser >> [{}]", user);

        Map<String, Object> returnMap = userService.createUser(user);

        boolean error = (Boolean) returnMap.get("error");
        if (error) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(returnMap);
        }
        returnMap.put("message", returnMap.get("message"));
        return ResponseEntity.status(HttpStatus.CREATED).body(returnMap);

    }


}

