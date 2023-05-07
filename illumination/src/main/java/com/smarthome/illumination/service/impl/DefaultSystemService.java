package com.smarthome.illumination.service.impl;

import com.smarthome.illumination.exception.SystemAlreadyRegisteredException;
import com.smarthome.illumination.exception.SystemRegisteredServiceException;
import com.smarthome.illumination.facade.data.SystemsData;
import com.smarthome.illumination.facade.data.UserData;
import com.smarthome.illumination.repository.SystemsDAO;
import com.smarthome.illumination.repository.model.SystemsModel;
import com.smarthome.illumination.repository.model.UserModel;
import com.smarthome.illumination.service.SystemsService;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DefaultSystemService implements SystemsService {

    private SystemsDAO systemsDAO;

    public DefaultSystemService(SystemsDAO systemsDAO) {
        this.systemsDAO = systemsDAO;
    }

    @Override
    public void addSystem(String systemId, UserModel userModel) throws SystemRegisteredServiceException {
        try {
            systemsDAO.addSystemToDataBase(Integer.valueOf(systemId), userModel);
        } catch (SystemAlreadyRegisteredException ex) {
            throw new SystemRegisteredServiceException("System already Exists");
        }
    }

    @Override
    public SystemsData getSystem(int id) throws ParseException {
        return getSystemDataFromSystemModel(systemsDAO.getSystemFromDataBase(id));
    }

    @Override
    public SystemsData getSystemDataFromSystemModel(SystemsModel systemsModel) throws ParseException {
        Date date=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(systemsModel.getStartDate());
        SystemsData systemsData=new SystemsData();
        systemsData.setId(systemsModel.getId());
        systemsData.setStatus(systemsModel.getSystemStatus());
        systemsData.setLight(systemsModel.getLightValue());
        systemsData.setStartDate(date);
        return systemsData;
    }

}