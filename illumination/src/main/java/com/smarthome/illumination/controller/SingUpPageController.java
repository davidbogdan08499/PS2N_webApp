package com.smarthome.illumination.controller;

import com.smarthome.illumination.constants.ConstantsVariables;
import com.smarthome.illumination.forms.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping
public class SingUpPageController {

    @GetMapping("/singUp")
    public String getSingInPage(Model model) {
        RegisterForm registerForm = new RegisterForm();
        model.addAttribute("registerForm", registerForm);
        return ConstantsVariables.SINGUPPAGE;


    }

    @PostMapping("/requestSingUp")
    public String getHomePage(@ModelAttribute("registerForm") RegisterForm registerForm,Model model){
        model.addAttribute(registerForm);
     return ConstantsVariables.REDIRECT+ConstantsVariables.SINGUPPAGE;
    }

}
