package com.sensor.service.domain;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.sensor.service.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nisha on 11/20/16.
 */
public class VirtualSensorDAO {

    private List<VirtualSensor> virtual_sensor_list=new ArrayList<>();
    private List<VirtualSensor> virtualSensorResult=new ArrayList<>();
    private List<Sensor> sensorResult=new ArrayList<>();
    private List<Vendor> vendorResult=new ArrayList<>();

    static AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider(ApiConstants.path,ApiConstants.profile)).withRegion(Regions.US_WEST_2);

    public void createSensor(VirtualSensor virtualSensor){

        DynamoDBMapper mapper=new DynamoDBMapper(client);

        List<VirtualSensor> sensorScanList=new ArrayList<>();
        DynamoDBScanExpression scanExpression=new DynamoDBScanExpression().withLimit(50);
        sensorScanList=mapper.scan(VirtualSensor.class,scanExpression);
        int id=sensorScanList.size()+1;

        VirtualSensor newVirtualSensor=new VirtualSensor();
        newVirtualSensor.setVirtualSensorId(id);
        newVirtualSensor.setVirtualSensorName(virtualSensor.getVirtualSensorName());
        newVirtualSensor.setSensorId(virtualSensor.getSensorId());
        newVirtualSensor.setUserEmail(virtualSensor.getUserEmail());

        mapper.save(newVirtualSensor);

    }

    public List virtualSensorList(String user_email){

        try {

            DynamoDBMapper mapper = new DynamoDBMapper(client);

            if(!(user_email.equals("0"))) {

                    Map<String, AttributeValue> filterMap = new HashMap<>();
                    filterMap.put(":val1", new AttributeValue().withS(user_email));

                    DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                            .withFilterExpression("user_email=:val1").withExpressionAttributeValues(filterMap);
                    virtualSensorResult = mapper.scan(VirtualSensor.class, scanExpression);

            } else {
                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withLimit(50);
                virtualSensorResult=mapper.scan(VirtualSensor.class,scanExpression);

            }

            for(int i=0;i<virtualSensorResult.size();i++){

                Map<String, AttributeValue> filterMap1 = new HashMap<>();
                filterMap1.put(":val2", new AttributeValue().withN(String.valueOf(virtualSensorResult.get(i).getSensorId())));

                DynamoDBScanExpression sensorScanExpression = new DynamoDBScanExpression()
                        .withFilterExpression("sensor_id=:val2").withExpressionAttributeValues(filterMap1);
                sensorResult=mapper.scan(Sensor.class,sensorScanExpression);

                Map<String, AttributeValue> filterMap2 = new HashMap<>();
                filterMap2.put(":val3", new AttributeValue().withS(String.valueOf(sensorResult.get(0).getVendorEmail())));

                DynamoDBScanExpression vendorScanExpression = new DynamoDBScanExpression()
                        .withFilterExpression("vendor_email=:val3").withExpressionAttributeValues(filterMap2);
                vendorResult=mapper.scan(Vendor.class,vendorScanExpression);



                VirtualSensor virtualsensorObj=new VirtualSensor();
                virtualsensorObj.setSensorId(virtualSensorResult.get(i).getSensorId());
                virtualsensorObj.setVirtualSensorName(virtualSensorResult.get(i).getVirtualSensorName());
                virtualsensorObj.setVirtualSensorId(virtualSensorResult.get(i).getVirtualSensorId());
                virtualsensorObj.setUserEmail(virtualSensorResult.get(i).getUserEmail());

                virtualsensorObj.setSensorName(sensorResult.get(0).getSensorName());
                virtualsensorObj.setSensorType(sensorResult.get(0).getSensorType());
                virtualsensorObj.setSensorLocation(sensorResult.get(0).getSensorLocation());
                virtualsensorObj.setSensorStatus(sensorResult.get(0).getSensorStatus());

                virtualsensorObj.setVendorName(vendorResult.get(0).getVendorName());

                virtual_sensor_list.add(virtualsensorObj);
            }

            /*for(int i=0;i<virtualSensorResult.size();i++){
                Map<String, AttributeValue> filterMap1 = new HashMap<>();
                filterMap1.put(":val2", new AttributeValue().withN(String.valueOf(virtualSensorResult.get(i).getSensorId())));

                DynamoDBScanExpression sensorScanExpression = new DynamoDBScanExpression()
                        .withFilterExpression("sensor_id=:val2").withExpressionAttributeValues(filterMap1);
                sensorResult=mapper.scan(Sensor.class,sensorScanExpression);

                VirtualSensor virtualsensorObj=new VirtualSensor();
                virtualsensorObj.setSensorName(sensorResult.get(0).getSensorName());
                virtualsensorObj.setSensorType(sensorResult.get(0).getSensorType());
                virtualsensorObj.setSensorLocation(sensorResult.get(0).getSensorLocation());
                virtualsensorObj.setSensorStatus(sensorResult.get(0).getSensorStatus());
                virtual_sensor_list.add(virtualsensorObj);

            }

            for(int i=0;i<sensorResult.size();i++){

                Map<String, AttributeValue> filterMap2 = new HashMap<>();
                filterMap2.put(":val3", new AttributeValue().withS(String.valueOf(sensorResult.get(i).getVendorEmail())));

                DynamoDBScanExpression vendorScanExpression = new DynamoDBScanExpression()
                        .withFilterExpression("vendor_email=:val3").withExpressionAttributeValues(filterMap2);
                vendorResult=mapper.scan(Vendor.class,vendorScanExpression);

                VirtualSensor virtualsensorObj=new VirtualSensor();
                virtualsensorObj.setVendorName(vendorResult.get(0).getVendorName());
                virtual_sensor_list.add(virtualsensorObj);

            }*/


        } catch (Throwable t) {
            t.printStackTrace();
        }

        return virtual_sensor_list;
    }

    public VirtualSensor findByVirtualSensorId(int virtual_sensor_id){

        DynamoDBMapper mapper = new DynamoDBMapper(client);

        Map<String, AttributeValue> filterMap = new HashMap<>();
        filterMap.put(":val1", new AttributeValue().withN(String.valueOf(virtual_sensor_id)));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("virtual_sensor_id=:val1").withExpressionAttributeValues(filterMap);
        virtualSensorResult=mapper.scan(VirtualSensor.class,scanExpression);

        VirtualSensor virtualsensorObj=null;
        if(!(virtualSensorResult.size()==0)) {
            virtualsensorObj = new VirtualSensor();
            virtualsensorObj.setVirtualSensorId(virtualSensorResult.get(0).getVirtualSensorId());
            virtualsensorObj.setVirtualSensorName(virtualSensorResult.get(0).getVirtualSensorName());
            virtualsensorObj.setSensorId(virtualSensorResult.get(0).getSensorId());
            virtualsensorObj.setUserEmail(virtualSensorResult.get(0).getUserEmail());
        }

        return virtualsensorObj;
    }

    public void deleteVirtualSensor(int virtual_sensor_id){

        DynamoDBMapper mapper=new DynamoDBMapper(client);
        VirtualSensor virtualsensor=mapper.load(VirtualSensor.class,virtual_sensor_id);
        mapper.delete(virtualsensor);
    }


}
