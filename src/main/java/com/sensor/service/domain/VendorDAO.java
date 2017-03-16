package com.sensor.service.domain;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.sensor.service.model.ApiConstants;
import com.sensor.service.model.Vendor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nisha on 11/12/16.
 */
public class VendorDAO {

    //public static String profile_path="../resources/credentials";
    //public static String profile_name="default";
    private List<Vendor> vendor_list=new ArrayList<>();
    private List<Vendor> vendorResult=new ArrayList<>();
    static AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider(ApiConstants.path,ApiConstants.profile))
            .withRegion(Regions.US_WEST_2);


    public List vendorList(String vendorEmail){

        try {
            DynamoDBMapper mapper = new DynamoDBMapper(client);

            if(!(vendorEmail.equals("0"))) {

                Map<String, AttributeValue> filterMap = new HashMap<>();
                filterMap.put(":val1", new AttributeValue().withS(vendorEmail));

                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                        .withFilterExpression("vendor_email=:val1").withExpressionAttributeValues(filterMap);
                vendorResult=mapper.scan(Vendor.class,scanExpression);

            }else {
                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withLimit(50);
                vendorResult = mapper.scan(Vendor.class, scanExpression);
            }

            for(int i=0;i<vendorResult.size();i++){
                Vendor vendorObj=new Vendor();
                vendorObj.setVendorName(vendorResult.get(i).getVendorName());
                vendorObj.setVendorEmail(vendorResult.get(i).getVendorEmail());
                vendorObj.setVendorPassword(vendorResult.get(i).getVendorPassword());
                vendor_list.add(vendorObj);

            }

        } catch (Throwable t) {
            t.printStackTrace();
        }

        return vendor_list;
    }

    public void createVendor(Vendor vendor){

        Vendor newVendorItem=new Vendor();

        DynamoDBMapper mapper=new DynamoDBMapper(client);

        newVendorItem.setVendorName(vendor.getVendorName());
        newVendorItem.setVendorEmail(vendor.getVendorEmail());
        newVendorItem.setVendorPassword(vendor.getVendorPassword());

        mapper.save(newVendorItem);

    }

}
