package com.smarthome.illumination.facade.impl;

import com.smarthome.illumination.exception.FacadeException;
import com.smarthome.illumination.exception.SystemNotAvailableException;
import com.smarthome.illumination.exception.SystemRegisteredServiceException;
import com.smarthome.illumination.exception.SystemServiceException;
import com.smarthome.illumination.facade.SystemsFacade;
import com.smarthome.illumination.facade.data.SystemsData;
import com.smarthome.illumination.facade.data.UserData;
import com.smarthome.illumination.repository.model.UserModel;
import com.smarthome.illumination.service.SystemsService;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class DefaultSystemFacade implements SystemsFacade {

    private SystemsService systemsService;

    public DefaultSystemFacade(SystemsService systemsService) {
        this.systemsService = systemsService;
    }

    @Override
    public void addSystem(String systemId, UserModel userModel) {
        try {
            systemsService.addSystem(systemId, userModel);
        } catch (SystemRegisteredServiceException e) {
            throw new FacadeException(e.getMessage());
        }
    }

    @Override
    public SystemsData getSystemOfUser(int id) throws ParseException, SystemNotAvailableException, SystemServiceException {
        try {
            return systemsService.getSystem(id);
        }catch (SystemServiceException exception){
            throw new FacadeException(exception.getMessage());
        }
    }

    @Override
    public void setPowerStatus(int systemID,String status) {
       systemsService.setPowerStatusForChosenSystem(systemID,status);
    }
}
