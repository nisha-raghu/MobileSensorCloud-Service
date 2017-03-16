package com.sensor.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Created by nisha on 11/12/16.
 */

@DynamoDBTable(tableName="user")
public class Users {

    private String userName;
    private String userEmail;
    private String userPassword;
    private int planId;

    @DynamoDBAttribute(attributeName="user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @DynamoDBHashKey(attributeName="user_email")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @DynamoDBAttribute(attributeName="user_password")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @DynamoDBAttribute(attributeName = "plan_id")
    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int plainId) {
        this.planId = plainId;
    }
}
