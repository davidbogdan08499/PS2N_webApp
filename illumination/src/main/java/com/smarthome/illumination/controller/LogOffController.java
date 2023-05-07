package com.smarthome.illumination.controller;

import com.smarthome.illumination.constants.ConstantsVariables;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LogOffController {

    @GetMapping("/logOff")
    public String getSingUpPage(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        return ConstantsVariables.REDIRECT + ConstantsVariables.SINGUPPAGE;
    }
}
