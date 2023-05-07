package com.smarthome.illumination.forms;

import org.springframework.stereotype.Component;

@Component
public class SystemForm {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
