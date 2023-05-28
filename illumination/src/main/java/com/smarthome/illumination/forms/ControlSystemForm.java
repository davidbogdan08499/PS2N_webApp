package com.smarthome.illumination.forms;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class ControlSystemForm {

    private String status;

    private String startDate;

    private int systemId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }
}
