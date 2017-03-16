package com.sensor.service.model;

import java.util.Date;
import java.util.Hashtable;

/**
 * Created by nisha on 11/20/16.
 */
public class SensorDataResponse {

    Hashtable<Date, Integer> sensorData;
    String sensorDataPoint;

    public Hashtable<Date, Integer> getSensorData() {
        return sensorData;
    }

    public void setSensorData(Hashtable<Date, Integer> sensorData) {
        this.sensorData = sensorData;
    }

    public String getSensorDataPoint() {
        return sensorDataPoint;
    }

    public void setSensorDataPoint(String sensorDataPoint) {
        this.sensorDataPoint = sensorDataPoint;
    }
}
