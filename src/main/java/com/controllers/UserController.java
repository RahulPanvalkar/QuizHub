package com.controllers;

import com.entities.User;
import com.services.impl.DefaultUserCreation;
import com.utility.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class UserController {

    private static final Logger logger = LoggerUtil.getLogger(UserController.class);
    @Autowired
    DefaultUserCreation userCreation;


    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        // API URL for adding user
        String url = "http://localhost:8080/QuizHub/api/add-user"; // Replace with the actual URL of your add-user API

        // Call the add-user API
        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(user), Map.class);

        // Check response status
        if (response.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("message", "User registered successfully.");
            return "redirect:/login";  // Redirect to login page after successful registration
        } else {
            model.addAttribute("error", response.getBody());  // Display error message
            return "register";  // Return to the registration page
        }
    }

}
