package com.sensor.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="vendor")
public class Vendor {

    private String vendorName;
    private String vendorEmail;
    private String vendorPassword;


    @DynamoDBAttribute(attributeName="vendor_name")
    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    @DynamoDBHashKey(attributeName="vendor_email")
    public String getVendorEmail() {
        return vendorEmail;
    }
    public void setVendorEmail(String vendorEmail) {
        this.vendorEmail = vendorEmail;
    }

    @DynamoDBAttribute(attributeName = "vendor_password")
    public String getVendorPassword() {
        return vendorPassword;
    }

    public void setVendorPassword(String vendorPassword) {
        this.vendorPassword = vendorPassword;
    }
}
