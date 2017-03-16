package com.sensor.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Created by nisha on 11/14/16.
 */

@DynamoDBTable(tableName = "sensor")
public class Sensor {

    private int sensorId;
    private String sensorName;
    private String sensorType;
    private Boolean sensorStatus;
    private String sensorLocation;
    private String vendorEmail;
    private String userEmail;


    @DynamoDBHashKey(attributeName="sensor_id")
    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    @DynamoDBAttribute(attributeName="sensor_name")
    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    @DynamoDBAttribute(attributeName="sensor_type")
    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    @DynamoDBAttribute(attributeName="sensor_status")
    public Boolean getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(Boolean sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    @DynamoDBAttribute(attributeName="sensor_location")
    public String getSensorLocation() {
        return sensorLocation;
    }

    public void setSensorLocation(String sensorLocation) {
        this.sensorLocation = sensorLocation;
    }

    @DynamoDBAttribute(attributeName="vendor_email")
    public String getVendorEmail() {
        return vendorEmail;
    }

    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    @DynamoDBAttribute(attributeName = "user_email")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
