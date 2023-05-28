package com.smarthome.illumination.controller;

import com.smarthome.illumination.constants.ConstantsVariables;
import com.smarthome.illumination.facade.UserFacade;
import com.smarthome.illumination.facade.data.UserData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ViewProfilePageController {

    private UserFacade userFacade;

    public ViewProfilePageController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping("/viewProfile")
    public String getViewProfilePage(HttpServletRequest httpServletRequest, Model model) {
        UserData currentUser = userFacade.getUserFromCurrentSession(httpServletRequest);
        userFacade.updateUserFromCurrentSession(httpServletRequest);
        if (currentUser == null) {
            return ConstantsVariables.REDIRECT + ConstantsVariables.SINGUPPAGE;
        }
        model.addAttribute("currentUser", currentUser);
        return ConstantsVariables.VIEWPROFILE;
    }
}
