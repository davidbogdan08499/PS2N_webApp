package com.smarthome.illumination.service;

import com.smarthome.illumination.exception.UserAlreadyAvailableException;
import com.smarthome.illumination.exception.UserAvailableServiceException;
import com.smarthome.illumination.exception.UserNotAvailableServiceException;
import com.smarthome.illumination.facade.data.UserData;
import com.smarthome.illumination.forms.LoginForm;
import com.smarthome.illumination.forms.RegisterForm;
import com.smarthome.illumination.repository.model.UserModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface UserService {

    UserModel getUserModelFromRegisterForm(RegisterForm registerForm) throws NoSuchAlgorithmException, InvalidKeySpecException;
    void createUser(UserModel userModel) throws  UserAvailableServiceException;

   UserData getUserDataFromUserModel(UserModel userModel);

   UserModel getUserFromDataBase(UserData userData);

   UserData verifyUserFromLogin(String mail,String password) throws InvalidKeySpecException, NoSuchAlgorithmException;



}
