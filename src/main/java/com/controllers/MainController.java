package com.controllers;

import com.dao.UserDao;
import com.services.DefaultUserCreation;
import com.utility.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class MainController {

    private static final Logger logger = LoggerUtil.getLogger(MainController.class);

    @Autowired
    DefaultUserCreation userCreation;

    @RequestMapping({"/","home"})
    public String getHome(){
        logger.info("home is loading..");
        return "public/home";
    }

    @RequestMapping("login")
    public String getLogin(){
        logger.info("login is loading..");
        return "public/login";
    }

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("user/beans")
    public String showBeans() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames); // Sort alphabetically
        for (String beanName : beanNames) {
            logger.info(beanName);
        }
        return "public/home";
    }

    @RequestMapping("admin/add-default-user")
    public String addDefaultUser(){
        logger.info("addDefaultUser is called..");
        userCreation.createNormalUser();
        return "public/home";
    }

    @GetMapping("logout")
    public String logoutAdmin(HttpServletRequest request) {
        logger.info("logout..");

        // Invalidate the session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/home";
    }
}
