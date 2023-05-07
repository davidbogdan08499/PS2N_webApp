package com.smarthome.illumination.facade;

import com.smarthome.illumination.exception.UserAlreadyAvailableException;
import com.smarthome.illumination.exception.UserAvailableServiceException;
import com.smarthome.illumination.exception.UserNotAvailableServiceException;
import com.smarthome.illumination.facade.data.UserData;
import com.smarthome.illumination.forms.LoginForm;
import com.smarthome.illumination.forms.RegisterForm;
import com.smarthome.illumination.repository.model.UserModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Interface for creating new user account
 */
public interface UserFacade {

    /**
     *
     * @param registerForm object that contains information from register form
     * @return true is the account was created with success or false
     */
    boolean createUser(RegisterForm registerForm) throws NoSuchAlgorithmException, InvalidKeySpecException;

    UserData getUserFromRegisterForm();

    UserModel getUserFromDataBase(UserData userData);

    UserData verifyUser(LoginForm loginForm) throws InvalidKeySpecException, NoSuchAlgorithmException;

    UserData getUserDataFromUserModel(UserModel userModel);

}
