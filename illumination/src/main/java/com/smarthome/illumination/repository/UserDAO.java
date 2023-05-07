package com.smarthome.illumination.repository;

import com.smarthome.illumination.exception.UserAlreadyAvailableException;
import com.smarthome.illumination.repository.model.UserModel;

public interface UserDAO {

    boolean saveUser(UserModel userModel) throws UserAlreadyAvailableException;

    UserModel getUserFromDataBase(String username,String mail);

    UserModel getUserForLogin(String mail);
}
