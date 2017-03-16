package com.sensor.service.domain;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.sensor.service.model.ApiConstants;
import com.sensor.service.model.Sensor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nisha on 11/14/16.
 */
public class SensorDAO {
    private List<Sensor> sensor_list=new ArrayList<>();
    private List<Sensor> sensorResult=new ArrayList<>();
    static AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider(ApiConstants.path,ApiConstants.profile)).withRegion(Regions.US_WEST_2);

    public List sensorList(String vendorEmail){

        try {

            DynamoDBMapper mapper = new DynamoDBMapper(client);

            if(!(vendorEmail.equals("0"))) {
                Map<String, AttributeValue> filterMap = new HashMap<>();
                filterMap.put(":val1", new AttributeValue().withS(vendorEmail));

                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                        .withFilterExpression("vendor_email=:val1").withExpressionAttributeValues(filterMap);
                sensorResult=mapper.scan(Sensor.class,scanExpression);

            }else{
                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withLimit(50);
                sensorResult=mapper.scan(Sensor.class,scanExpression);
            }



            for(int i=0;i<sensorResult.size();i++){
                Sensor sensorObj=new Sensor();
                sensorObj.setSensorId(sensorResult.get(i).getSensorId());
                sensorObj.setSensorName(sensorResult.get(i).getSensorName());
                sensorObj.setSensorType(sensorResult.get(i).getSensorType());
                sensorObj.setSensorStatus(sensorResult.get(i).getSensorStatus());
                sensorObj.setSensorLocation(sensorResult.get(i).getSensorLocation());
                sensorObj.setVendorEmail(sensorResult.get(i).getVendorEmail());
                sensorObj.setUserEmail(sensorResult.get(i).getUserEmail());
                sensor_list.add(sensorObj);

            }

        } catch (Throwable t) {
            t.printStackTrace();
        }

        return sensor_list;
    }

    public void createSensor(Sensor sensor){

        DynamoDBMapper mapper=new DynamoDBMapper(client);

        List<Sensor> sensorScanList=new ArrayList<>();
        DynamoDBScanExpression scanExpression=new DynamoDBScanExpression().withLimit(50);
        sensorScanList=mapper.scan(Sensor.class,scanExpression);
        int id=sensorScanList.size()+1;

        Sensor newSensorItem=new Sensor();

        newSensorItem.setSensorId(id);
        newSensorItem.setSensorName(sensor.getSensorName());
        newSensorItem.setSensorType(sensor.getSensorType());
        newSensorItem.setSensorStatus(sensor.getSensorStatus());
        newSensorItem.setSensorLocation(sensor.getSensorLocation());
        newSensorItem.setVendorEmail(sensor.getVendorEmail());
        newSensorItem.setUserEmail(sensor.getUserEmail());

        mapper.save(newSensorItem);

    }

    public Sensor findBySensorId(int sensor_id){

        DynamoDBMapper mapper = new DynamoDBMapper(client);

        Map<String, AttributeValue> filterMap = new HashMap<>();
        filterMap.put(":val1", new AttributeValue().withN(String.valueOf(sensor_id)));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("sensor_id=:val1").withExpressionAttributeValues(filterMap);
        sensorResult=mapper.scan(Sensor.class,scanExpression);

        Sensor sensorObj=null;
        if(!(sensorResult.size()==0)) {
            sensorObj = new Sensor();
            sensorObj.setSensorId(sensorResult.get(0).getSensorId());
            sensorObj.setSensorName(sensorResult.get(0).getSensorName());
            sensorObj.setSensorType(sensorResult.get(0).getSensorType());
            sensorObj.setSensorStatus(sensorResult.get(0).getSensorStatus());
            sensorObj.setSensorLocation(sensorResult.get(0).getSensorLocation());
            sensorObj.setVendorEmail(sensorResult.get(0).getVendorEmail());
            sensorObj.setUserEmail(sensorResult.get(0).getUserEmail());
        }

        return sensorObj;
    }

    public void updateSensor(Sensor sensor){

        DynamoDBMapper mapper = new DynamoDBMapper(client);
        mapper.save(sensor);

    }

    public void deleteSensor(int sensor_id){

        DynamoDBMapper mapper=new DynamoDBMapper(client);
        Sensor sensor=mapper.load(Sensor.class,sensor_id);
        mapper.delete(sensor);
    }
}
