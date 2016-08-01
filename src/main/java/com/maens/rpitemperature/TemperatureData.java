package com.maens.rpitemperature;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Marcus on 2016-08-01.
 */
@Entity
@Table(name="TemperatureData")

public class TemperatureData {
    @Id
    @GeneratedValue
    private long id;
    private String Location;
    private Double Temperature;
    private Date timestamp;

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Double getTemperature() {
        return Temperature;
    }

    public void setTemperature(Double temperature) {
        Temperature = temperature;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
