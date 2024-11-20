package com.services.impl;

import com.dao.UserDao;
import com.entities.CustomUserDetail;
import com.entities.User;
import com.utility.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    private static final Logger logger = LoggerUtil.getLogger(CustomUserDetailsService.class);

    // to log in using both id and email
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername >> username : "+username);
        Optional<User> userOpt;
        if (username.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
            userOpt = userDao.findByEmailId(username);
        }else{
            try {
                userOpt = userDao.findByUsername(username);
            } catch (NumberFormatException e) {
                throw new UsernameNotFoundException("Invalid user ID format");
            }
        }

        if (userOpt.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        logger.info("UserOpt : {}",userOpt);
        return new CustomUserDetail(userOpt.get());
    }
}

