package com.maens.rpitemperature;

import com.maens.rpitemperature.entities.EntityTemperatureData;

import java.util.Date;
import java.util.List;

interface TemperatureDAO {
    List<EntityTemperatureData> getTempByDate(Date date);
    List<EntityTemperatureData> getTempByMonth(int month);
    void addTemperatureData(EntityTemperatureData temperatureData);
}
