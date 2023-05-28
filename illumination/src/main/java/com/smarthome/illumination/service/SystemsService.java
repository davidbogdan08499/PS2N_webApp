package com.smarthome.illumination.service;

import com.smarthome.illumination.exception.SystemNotAvailableException;
import com.smarthome.illumination.exception.SystemRegisteredServiceException;
import com.smarthome.illumination.exception.SystemServiceException;
import com.smarthome.illumination.facade.SystemsFacade;
import com.smarthome.illumination.facade.data.SystemsData;
import com.smarthome.illumination.facade.data.UserData;
import com.smarthome.illumination.forms.RegisterForm;
import com.smarthome.illumination.repository.SystemsDAO;
import com.smarthome.illumination.repository.model.SystemsModel;
import com.smarthome.illumination.repository.model.UserModel;

import java.text.ParseException;

public interface SystemsService {

   void addSystem(String systemService, UserModel userModel) throws SystemRegisteredServiceException;

   SystemsData getSystem(int id) throws ParseException, SystemNotAvailableException, SystemServiceException;

   SystemsData getSystemDataFromSystemModel(SystemsModel systemsModel) throws ParseException;

   void setPowerStatusForChosenSystem(int id,String status);
}
