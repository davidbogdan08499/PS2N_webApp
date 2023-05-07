package com.smarthome.illumination.controller;

import com.smarthome.illumination.constants.ConstantsVariables;
import com.smarthome.illumination.exception.FacadeException;
import com.smarthome.illumination.facade.SystemsFacade;
import com.smarthome.illumination.facade.UserFacade;
import com.smarthome.illumination.facade.data.UserData;
import com.smarthome.illumination.forms.LoginForm;
import com.smarthome.illumination.forms.RegisterForm;
import com.smarthome.illumination.repository.model.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
@RequestMapping
public class SingUpPageController {

    private final UserFacade userFacade;
    private final SystemsFacade systemsFacade;

    public SingUpPageController(UserFacade userFacade, SystemsFacade systemsFacade) {
        this.userFacade = userFacade;
        this.systemsFacade = systemsFacade;
    }

    @GetMapping("/singUp")
    public String getSingInPage(Model model, HttpServletRequest httpServletRequest) {
        RegisterForm registerForm = new RegisterForm();
        LoginForm loginForm = new LoginForm();
        model.addAttribute("registerForm", registerForm);
        model.addAttribute("loginForm", loginForm);
        return ConstantsVariables.SINGUPPAGE;


    }

    @PostMapping(value = "/requestSingUp")
    public String getHomePage(@ModelAttribute("registerForm") RegisterForm registerForm, Model model, HttpServletRequest httpServletRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {
        model.addAttribute(registerForm);
        UserData userData;
        try {
            userFacade.createUser(registerForm);
            userData = userFacade.getUserFromRegisterForm();
            httpServletRequest.getSession().setAttribute("currentUser", userData);
            UserModel userModel = userFacade.getUserFromDataBase(userData);
            systemsFacade.addSystem(registerForm.getSystemID(), userModel);
            return ConstantsVariables.REDIRECT + ConstantsVariables.HOME;
        } catch (FacadeException ex) {
            return ConstantsVariables.REDIRECT + ConstantsVariables.SINGUPPAGE;
        }
    }
}

