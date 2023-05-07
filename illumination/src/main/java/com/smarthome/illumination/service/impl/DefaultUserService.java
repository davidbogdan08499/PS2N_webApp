package com.smarthome.illumination.service.impl;

import com.smarthome.illumination.exception.FacadeException;
import com.smarthome.illumination.exception.UserAlreadyAvailableException;
import com.smarthome.illumination.exception.UserAvailableServiceException;
import com.smarthome.illumination.exception.UserNotAvailableServiceException;
import com.smarthome.illumination.facade.UserFacade;
import com.smarthome.illumination.facade.data.UserData;
import com.smarthome.illumination.forms.LoginForm;
import com.smarthome.illumination.forms.RegisterForm;
import com.smarthome.illumination.repository.UserDAO;
import com.smarthome.illumination.repository.model.SystemsModel;
import com.smarthome.illumination.repository.model.UserModel;
import com.smarthome.illumination.service.UserService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;

@Component
public class DefaultUserService implements UserService {

    private UserDAO userDAO;

    public DefaultUserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserModel getUserModelFromRegisterForm(RegisterForm registerForm) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserModel userModel = new UserModel();
        userModel.setUsername(registerForm.getUserName());
        userModel.setEmail(registerForm.getEmail());
        setHashPasswword(registerForm, userModel);
        userModel.setSystemsModelList(new ArrayList<SystemsModel>());
        return userModel;
    }

    @Override
    public void createUser(UserModel userModel) throws UserAvailableServiceException {
        try {
            userDAO.saveUser(userModel);
        } catch (UserAlreadyAvailableException e) {
            throw new UserAvailableServiceException("User exists!");
        }
    }

    @Override
    public UserData getUserDataFromUserModel(UserModel userModel) {
        UserData userData = new UserData();
        userData.setUsername(userModel.getUsername());
        userData.setEmail(userModel.getEmail());
        userData.setSystemsModelList(userModel.getSystemsModelList());
        return userData;
    }

    @Override
    public UserModel getUserFromDataBase(UserData userData) {
        UserModel userModel = userDAO.getUserFromDataBase(userData.getUsername(), userData.getEmail());
        return userModel;
    }


    private void setHashPasswword(RegisterForm registerForm, UserModel userModel)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        String newPassword;

        //generate salt value
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = secureRandom.generateSeed(12);
        userModel.setHash(Base64.getEncoder().encodeToString(salt));

        //hash password
        PBEKeySpec pbeKeySpec =
                new PBEKeySpec(registerForm.getPassword().toCharArray(), salt, 10, 512);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        newPassword = Base64.getMimeEncoder().encodeToString(hash);
        userModel.setPassword(newPassword);
        userModel.setConfirmPassword(newPassword);

    }

    private String encryptPasswordFromLogin(String plainPassword, String passwordSalt)
            throws InvalidKeySpecException, NoSuchAlgorithmException {

        String newPassword;
        byte[] salt = Base64.getMimeDecoder().decode(passwordSalt);
        PBEKeySpec pbeKeySpec =
                new PBEKeySpec(plainPassword.toCharArray(), salt, 10, 512);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = secretKeyFactory.generateSecret(pbeKeySpec).getEncoded();
        newPassword = Base64.getMimeEncoder().encodeToString(hash);
        return newPassword;
    }

    @Override
    public UserData verifyUserFromLogin(String mail, String password)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        UserModel userModel = userDAO.getUserForLogin(mail);
        UserData userData = null;

        if (encryptPasswordFromLogin(password, userModel.getHash()).equals(userModel.getPassword())) {
            userData = getUserDataFromUserModel(userModel);
        }
        return userData;
    }
}
