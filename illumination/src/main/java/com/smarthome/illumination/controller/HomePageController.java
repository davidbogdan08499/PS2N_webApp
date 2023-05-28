package com.smarthome.illumination.controller;

import com.smarthome.illumination.constants.ConstantsVariables;
import com.smarthome.illumination.facade.UserFacade;
import com.smarthome.illumination.facade.data.UserData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping()
public class HomePageController {

    private UserFacade userFacade;

    public HomePageController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping(value = "/home")
    public String getHomePage(HttpServletRequest httpServletRequest) {

        if (httpServletRequest.getSession().getAttribute("currentUser") == null) {
            return ConstantsVariables.REDIRECT + ConstantsVariables.SINGUPPAGE;
        }
        userFacade.updateUserFromCurrentSession(httpServletRequest);
        return ConstantsVariables.HOME;


    }
}
