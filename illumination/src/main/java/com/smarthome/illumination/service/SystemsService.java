package com.smarthome.illumination.service;

import com.smarthome.illumination.exception.SystemRegisteredServiceException;
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

   SystemsData getSystem(int id) throws ParseException;

   SystemsData getSystemDataFromSystemModel(SystemsModel systemsModel) throws ParseException;
}
