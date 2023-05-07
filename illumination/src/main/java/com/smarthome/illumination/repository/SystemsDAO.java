package com.smarthome.illumination.repository;

import com.smarthome.illumination.exception.SystemAlreadyRegisteredException;
import com.smarthome.illumination.exception.SystemRegisteredServiceException;
import com.smarthome.illumination.repository.model.SystemsModel;
import com.smarthome.illumination.repository.model.UserModel;

/**
 * Interface for working with users and database
 */
public interface SystemsDAO {

    boolean addSystemToDataBase(int systemID, UserModel userModel) throws  SystemAlreadyRegisteredException;

    SystemsModel getSystemFromDataBase(int id);
}
