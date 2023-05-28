package com.smarthome.illumination.controller;

import com.smarthome.illumination.constants.ConstantsVariables;
import com.smarthome.illumination.exception.FacadeException;
import com.smarthome.illumination.exception.SystemNotAvailableException;
import com.smarthome.illumination.exception.SystemServiceException;
import com.smarthome.illumination.facade.SystemsFacade;
import com.smarthome.illumination.facade.UserFacade;
import com.smarthome.illumination.facade.data.SystemsData;
import com.smarthome.illumination.facade.data.UserData;
import com.smarthome.illumination.forms.ControlSystemForm;
import com.smarthome.illumination.repository.model.SystemsModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;

@Controller
public class ChosenSystemPageController {

    private SystemsFacade systemsFacade;
    private UserFacade userFacade;


    public ChosenSystemPageController(SystemsFacade systemsFacade, UserFacade userFacade) {
        this.systemsFacade = systemsFacade;
        this.userFacade = userFacade;
    }

    @GetMapping("/selectedSystem")
    public String getSystemControlPage(HttpServletRequest httpServletRequest
            , @RequestParam int id
            , Model model
            , RedirectAttributes redirectAttributes) throws ParseException, SystemNotAvailableException, SystemServiceException {

        UserData currentUser = userFacade.getUserFromCurrentSession(httpServletRequest);
        ControlSystemForm controlSystemForm = new ControlSystemForm();
        if (currentUser == null) {
            return ConstantsVariables.REDIRECT + ConstantsVariables.SINGUPPAGE;
        }
        try {
            userFacade.updateUserFromCurrentSession(httpServletRequest);
            SystemsData currentSystem = systemsFacade.getSystemOfUser(id);
            controlSystemForm.setSystemId(id);
            model.addAttribute("currentSystem", currentSystem);
            model.addAttribute("controlSystemForm", controlSystemForm);
            return ConstantsVariables.CONTROLSYSTEM;
        } catch (FacadeException exception) {
            return ConstantsVariables.REDIRECT + ConstantsVariables.VIEWSYSTEMS;
        }
    }

    @PostMapping("/requestControlSystem")
    public String requestControlSystem(@ModelAttribute("controlSystemForm") ControlSystemForm controlSystemForm) {

        systemsFacade.setPowerStatus(controlSystemForm.getSystemId(),controlSystemForm.getStatus());
        return ConstantsVariables.REDIRECT + ConstantsVariables.VIEWSYSTEMS;
    }
}
