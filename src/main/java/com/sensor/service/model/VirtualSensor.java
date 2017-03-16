package com.sensor.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Created by nisha on 11/20/16.
 */

@DynamoDBTable(tableName = "user_sensor")
public class VirtualSensor {

    private int virtualSensorId;
    private String virtualSensorName;
    private int sensorId;
    private String sensorLocation;
    private String sensorName;
    private Boolean sensorStatus;
    private String sensorType;
    private String vendorName;
    private String userEmail;

    @DynamoDBHashKey(attributeName = "virtual_sensor_id")
    public int getVirtualSensorId() {
        return virtualSensorId;
    }

    public void setVirtualSensorId(int virtualSensorId) {
        this.virtualSensorId = virtualSensorId;
    }

    @DynamoDBAttribute(attributeName = "virtual_sensor_name")
    public String getVirtualSensorName() {
        return virtualSensorName;
    }

    public void setVirtualSensorName(String virtualSensorName) {
        this.virtualSensorName = virtualSensorName;
    }

    @DynamoDBAttribute(attributeName = "sensor_id")
    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorLocation() {
        return sensorLocation;
    }

    public void setSensorLocation(String sensorLocation) {
        this.sensorLocation = sensorLocation;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public Boolean getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(Boolean sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    @DynamoDBAttribute(attributeName = "user_email")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }



}
