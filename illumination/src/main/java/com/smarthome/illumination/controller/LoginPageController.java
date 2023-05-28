package com.smarthome.illumination.controller;

import com.smarthome.illumination.constants.ConstantsVariables;
import com.smarthome.illumination.exception.FacadeException;
import com.smarthome.illumination.facade.UserFacade;
import com.smarthome.illumination.facade.data.UserData;
import com.smarthome.illumination.forms.LoginForm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
@RequestMapping
public class LoginPageController {

    UserFacade userFacade;

    public LoginPageController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping(value = "/requestLogin", params = "Login")
    public String getHomePageFromLogin(@ModelAttribute("loginForm") LoginForm loginForm, HttpServletRequest httpServletRequest,
                                       Model model, RedirectAttributes redirectAttributes) throws InvalidKeySpecException, NoSuchAlgorithmException {
        model.addAttribute(loginForm);
        try {
            UserData userData = userFacade.verifyUser(loginForm);
            httpServletRequest.getSession().setAttribute("currentUser", userData);
            return ConstantsVariables.REDIRECT + ConstantsVariables.HOME;
        } catch (FacadeException exception) {
            redirectAttributes.addFlashAttribute("errorSingUp", exception.getMessage());
            return ConstantsVariables.REDIRECT + ConstantsVariables.SINGUPPAGE;
        }
    }
}
