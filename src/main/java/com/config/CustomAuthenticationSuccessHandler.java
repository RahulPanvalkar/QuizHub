package com.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Get roles from the authentication object
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        // Get the authenticated user details (UserDetails object)
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();  // Use authentication.getPrincipal()
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("admin/home");

            // Fetch admin details and set in session
//            String username = userDetails.getUsername();  // Get the username from the UserDetails object
//            Admin admin = adminService.getAdmin(username);  // Fetch admin from database using username
//            userSession.setAdmin(admin);  // Store Admin object in session

        } else if (roles.contains("ROLE_NORMAL")) {
            response.sendRedirect("user/home");

            // Fetch admin details and set in session
//            String username = userDetails.getUsername();  // Get the username from the UserDetails object
//            Consumer consumer = consumerService.getConsumer(username);  // Fetch consumer from database using username
//            userSession.setConsumer(consumer);  // Store Consumer object in session

        } else {
            response.sendRedirect("home"); // Default URL for other roles or if no role matches
        }
    }
}
