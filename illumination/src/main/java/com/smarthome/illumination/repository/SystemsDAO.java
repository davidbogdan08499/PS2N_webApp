package com.smarthome.illumination.repository;

import com.smarthome.illumination.exception.SystemAlreadyRegisteredException;
import com.smarthome.illumination.exception.SystemNotAvailableException;
import com.smarthome.illumination.exception.SystemRegisteredServiceException;
import com.smarthome.illumination.repository.model.SystemsModel;
import com.smarthome.illumination.repository.model.UserModel;

/**
 * Interface for working with users and database
 */
public interface SystemsDAO {

    boolean addSystemToDataBase(int systemID, UserModel userModel) throws  SystemAlreadyRegisteredException;

    SystemsModel getSystemFromDataBase(int id) throws SystemNotAvailableException;

     void setDataForChosenSystem(int systemID,String powerStatus);
}
