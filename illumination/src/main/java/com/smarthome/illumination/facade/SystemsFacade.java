package com.smarthome.illumination.facade;

import com.smarthome.illumination.exception.SystemNotAvailableException;
import com.smarthome.illumination.exception.SystemServiceException;
import com.smarthome.illumination.facade.data.SystemsData;
import com.smarthome.illumination.facade.data.UserData;
import com.smarthome.illumination.repository.model.UserModel;

import java.text.ParseException;

/**
 *
 */
public interface SystemsFacade {

    void addSystem(String systemId, UserModel userModel);

    SystemsData getSystemOfUser(int id) throws ParseException, SystemNotAvailableException, SystemServiceException;

    void setPowerStatus(int systemId,String status);

}
