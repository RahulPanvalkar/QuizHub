package com.controllers;

import com.utility.LoggerUtil;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    private static final Logger logger = LoggerUtil.getLogger(MainController.class);


    @RequestMapping({"/","home","admin/home","user/home"})
    public String getHome(){
        logger.info("home is loading..");
        return "public/home";
    }

    @RequestMapping("login")
    public String getLogin(){
        logger.info("login is loading..");
        return "public/login";
    }

    @GetMapping("error")
    public String getErrorPage(){
        logger.info("error is loading..");
        return "public/custom-404";
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
