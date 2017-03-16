package com.sensor.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;

/**
 * Created by nisha on 11/28/16.
 */

@DynamoDBTable(tableName = "user_sensor_group")
public class VirtualSensorGroup {

    int sensorGroupId;
    String sensorGroupName;
    String sensorGroupDescription;
    List<Integer> virtualSensorId;
    String userEmail;

    @DynamoDBHashKey(attributeName = "sensor_group_id")
    public int getSensorGroupId() {
        return sensorGroupId;
    }

    public void setSensorGroupId(int sensorGroupId) {
        this.sensorGroupId = sensorGroupId;
    }

    @DynamoDBAttribute(attributeName = "sensor_group_name")
    public String getSensorGroupName() {
        return sensorGroupName;
    }

    public void setSensorGroupName(String sensorGroupName) {
        this.sensorGroupName = sensorGroupName;
    }

    @DynamoDBAttribute(attributeName = "sensor_group_description")
    public String getSensorGroupDescription() {
        return sensorGroupDescription;
    }

    public void setSensorGroupDescription(String sensorGroupDescription) {
        this.sensorGroupDescription = sensorGroupDescription;
    }

    @DynamoDBAttribute(attributeName = "virtual_sensor_id")
    public List<Integer> getVirtualSensorId() {
        return virtualSensorId;
    }

    public void setVirtualSensorId(List<Integer> virtualSensorId) {
        this.virtualSensorId = virtualSensorId;
    }

    @DynamoDBAttribute(attributeName = "user_email")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
