package com.maens.rpitemperature.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="TemperatureData")

public class EntityTemperatureData {
    @Id
    @GeneratedValue
    private long id;
    private String location;
    private Double temperature;
    private Date timestamp;

    public EntityTemperatureData(){}
    public EntityTemperatureData(String location, Double temperature, Date timestamp){
        this.location = location;
        this.temperature = temperature;
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
       this.location = location;
    }

    public Double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "Location: "+this.location +", Temperature: "+ this.temperature +", Timestamp: "+this.timestamp;
    }

}
