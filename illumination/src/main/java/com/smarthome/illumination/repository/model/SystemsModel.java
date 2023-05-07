package com.smarthome.illumination.repository.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;

import javax.print.attribute.standard.MediaSize;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "systems")
public class SystemsModel implements Serializable {

    @Id
    @Column(name = "system_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @Column(name = "status")
    private String systemStatus;

    @Column(name = "light")
    private String lightValue;

    @Column(name = "start")
    private String startDate;
    public SystemsModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(String systemStatus) {
        this.systemStatus = systemStatus;
    }

    public String getLightValue() {
        return lightValue;
    }

    public void setLightValue(String lightValue) {
        this.lightValue = lightValue;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "SystemsModel{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
