package com.smarthome.illumination.facade.data;

import com.smarthome.illumination.repository.model.UserModel;

import java.util.Date;

public class SystemsData {
    private int id;

    private String status;

    private String light;

    private Date startDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
