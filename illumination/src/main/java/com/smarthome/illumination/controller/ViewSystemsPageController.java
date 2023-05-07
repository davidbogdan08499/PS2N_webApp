package com.smarthome.illumination.controller;

import com.smarthome.illumination.constants.ConstantsVariables;
import com.smarthome.illumination.exception.FacadeException;
import com.smarthome.illumination.facade.SystemsFacade;
import com.smarthome.illumination.facade.UserFacade;
import com.smarthome.illumination.facade.data.UserData;
import com.smarthome.illumination.forms.SystemForm;
import com.smarthome.illumination.repository.model.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ViewSystemsPageController {

    private final UserFacade userFacade;
    private final SystemsFacade systemsFacade;

    public ViewSystemsPageController(UserFacade userFacade, SystemsFacade systemsFacade) {
        this.userFacade = userFacade;
        this.systemsFacade = systemsFacade;
    }

    @GetMapping("/viewSystems")
    public String getViewSystemsPage(HttpServletRequest httpServletRequest, Model model) {
        UserData currentUser = (UserData) httpServletRequest.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            return ConstantsVariables.REDIRECT + ConstantsVariables.SINGUPPAGE;
        }
        SystemForm systemForm = new SystemForm();
        model.addAttribute("systemForm", systemForm);
        model.addAttribute("currentUser", currentUser);
        return ConstantsVariables.VIEWSYSTEMS;
    }

    @PostMapping("/addNewSystem")
    public String addNewSystem(HttpServletRequest httpServletRequest, Model model, @ModelAttribute("systemForm") SystemForm systemForm) {
        UserData currentUser = (UserData) httpServletRequest.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            return ConstantsVariables.REDIRECT + ConstantsVariables.SINGUPPAGE;
        }
        try {
            systemsFacade.addSystem(systemForm.getId(), userFacade.getUserFromDataBase(currentUser));
            currentUser = userFacade.getUserDataFromUserModel(userFacade.getUserFromDataBase(currentUser));
            httpServletRequest.getSession().setAttribute("currentUser", currentUser);
            return ConstantsVariables.REDIRECT + ConstantsVariables.VIEWSYSTEMS;
        } catch (FacadeException ex) {
            return ConstantsVariables.REDIRECT + ConstantsVariables.HOME;
        }
    }

}
