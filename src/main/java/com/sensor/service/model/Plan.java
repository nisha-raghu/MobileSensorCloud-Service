package com.sensor.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Created by nisha on 11/15/16.
 */

@DynamoDBTable(tableName = "user_plan")
public class Plan {

    private int planId;
    private String planName;
    private int planPrice;

    @DynamoDBHashKey(attributeName = "plan_id")
    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @DynamoDBHashKey(attributeName = "plan_name")
    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    @DynamoDBHashKey(attributeName = "price")
    public int getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(int planPrice) {
        this.planPrice = planPrice;
    }
}
