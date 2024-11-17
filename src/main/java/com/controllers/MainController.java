package com.controllers;

import com.utility.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private static final Logger logger = LoggerUtil.getLogger(MainController.class);

    @RequestMapping({"/","home"})
    public String getHome(){
        logger.info("home is loading..");
        return "public/home";
    }
}
