package com.smarthome.illumination.controller;

import com.smarthome.illumination.constants.ConstantsVariables;
import com.smarthome.illumination.facade.SystemsFacade;
import com.smarthome.illumination.facade.data.SystemsData;
import com.smarthome.illumination.facade.data.UserData;
import com.smarthome.illumination.forms.ControlSystemForm;
import com.smarthome.illumination.repository.model.SystemsModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
public class ChosenSystemPageController {

    private SystemsFacade systemsFacade;

    public ChosenSystemPageController(SystemsFacade systemsFacade) {
        this.systemsFacade = systemsFacade;
    }

    @GetMapping("/selectedSystem")
    public String getSystemControlPage(HttpServletRequest httpServletRequest, @RequestParam int id, Model model) throws ParseException {
        UserData currentUser = (UserData) httpServletRequest.getSession().getAttribute("currentUser");
        ControlSystemForm controlSystemForm=new ControlSystemForm();
        if (currentUser == null) {
            return ConstantsVariables.REDIRECT + ConstantsVariables.SINGUPPAGE;
        }
        SystemsData currentSystem=systemsFacade.getSystemOfUser(id);
        model.addAttribute("currentSystem",currentSystem);
        model.addAttribute("controlSystemForm",controlSystemForm);
        return ConstantsVariables.CONTROLSYSTEM;
    }

    @PostMapping("/requestControlSystem")
    public String requestControlSystem(@ModelAttribute("controlSystemForm")ControlSystemForm controlSystemForm){

        return ConstantsVariables.REDIRECT+ConstantsVariables.VIEWSYSTEMS;
    }
}
