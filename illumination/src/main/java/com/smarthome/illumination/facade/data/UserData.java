package com.smarthome.illumination.facade.data;

import com.smarthome.illumination.repository.model.SystemsModel;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class UserData implements Serializable {

    private String username;
    private String email;

    private List<SystemsModel> systemsModelList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SystemsModel> getSystemsModelList() {
        return systemsModelList;
    }

    public void setSystemsModelList(List<SystemsModel> systemsModelList) {
        this.systemsModelList = systemsModelList;
    }
}
