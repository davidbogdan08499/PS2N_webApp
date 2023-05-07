package com.smarthome.illumination.facade.impl;

import com.smarthome.illumination.exception.FacadeException;
import com.smarthome.illumination.exception.UserAlreadyAvailableException;
import com.smarthome.illumination.exception.UserAvailableServiceException;
import com.smarthome.illumination.exception.UserNotAvailableServiceException;
import com.smarthome.illumination.facade.UserFacade;
import com.smarthome.illumination.facade.data.UserData;
import com.smarthome.illumination.forms.LoginForm;
import com.smarthome.illumination.forms.RegisterForm;
import com.smarthome.illumination.repository.model.UserModel;
import com.smarthome.illumination.service.UserService;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class DefaultUserFacade implements UserFacade {

    private UserService userService;
    private UserModel userModelRegisterForm;

    public DefaultUserFacade(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean createUser(RegisterForm registerForm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        boolean verify = false;
        try {
            userModelRegisterForm = userService.getUserModelFromRegisterForm(registerForm);
            userService.createUser(userModelRegisterForm);
        } catch (UserAvailableServiceException exception) {
            throw new FacadeException("User exists!");
        }
        return verify;
    }

    @Override
    public UserData getUserFromRegisterForm() {
        return userService.getUserDataFromUserModel(userModelRegisterForm);
    }

    @Override
    public UserModel getUserFromDataBase(UserData userData) {
        return userService.getUserFromDataBase(userData);
    }


    public UserService getUserService() {
        return userService;
    }

    @Override
    public UserData verifyUser(LoginForm loginForm) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return userService.verifyUserFromLogin(loginForm.getMail(), loginForm.getPassword());
    }

    @Override
    public UserData getUserDataFromUserModel(UserModel userModel) {
        return userService.getUserDataFromUserModel(userModel);
    }
}
