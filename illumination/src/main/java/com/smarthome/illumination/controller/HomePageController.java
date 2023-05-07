package com.smarthome.illumination.controller;

import com.smarthome.illumination.constants.ConstantsVariables;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping()
public class HomePageController {

    @GetMapping(value = "/home")
    public String getHomePage(HttpServletRequest httpServletRequest) {

        if (httpServletRequest.getSession().getAttribute("currentUser") == null) {
            return ConstantsVariables.REDIRECT + ConstantsVariables.SINGUPPAGE;
        }
        return ConstantsVariables.HOME;


    }
}
