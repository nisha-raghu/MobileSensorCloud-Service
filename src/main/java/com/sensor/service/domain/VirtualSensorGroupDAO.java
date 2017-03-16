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
 * Created by nisha on 11/29/16.
 */
public class VirtualSensorGroupDAO {

    private List<VirtualSensorGroup> virtual_sensor_grp_list=new ArrayList<>();
    private List<VirtualSensorGroup> virtualSensorGrpResult=new ArrayList<>();

    static AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider(ApiConstants.path,ApiConstants.profile)).withRegion(Regions.US_WEST_2);

    public void createSensorGroup(VirtualSensorGroup virtualSensorGrp){

        DynamoDBMapper mapper=new DynamoDBMapper(client);

        List<VirtualSensorGroup> sensorGrpScanList=new ArrayList<>();
        DynamoDBScanExpression scanExpression=new DynamoDBScanExpression().withLimit(50);
        sensorGrpScanList=mapper.scan(VirtualSensorGroup.class,scanExpression);
        int id=sensorGrpScanList.size()+1;

        VirtualSensorGroup newVirtualSensorGrp=new VirtualSensorGroup();
        newVirtualSensorGrp.setSensorGroupId(id);
        newVirtualSensorGrp.setSensorGroupName(virtualSensorGrp.getSensorGroupName());
        newVirtualSensorGrp.setSensorGroupDescription(virtualSensorGrp.getSensorGroupDescription());
        newVirtualSensorGrp.setVirtualSensorId(virtualSensorGrp.getVirtualSensorId());
        newVirtualSensorGrp.setUserEmail(virtualSensorGrp.getUserEmail());

        mapper.save(newVirtualSensorGrp);

    }

    public List virtualSensorGroupList(String userEmail){

        DynamoDBMapper mapper = new DynamoDBMapper(client);

        if(!(userEmail.equals("0")))
        {
            Map<String, AttributeValue> filterMap = new HashMap<>();
            filterMap.put(":val1", new AttributeValue().withS(userEmail));

            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                    .withFilterExpression("user_email=:val1").withExpressionAttributeValues(filterMap);
            virtualSensorGrpResult=mapper.scan(VirtualSensorGroup.class,scanExpression);


        }else {

            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withLimit(50);
            virtualSensorGrpResult = mapper.scan(VirtualSensorGroup.class, scanExpression);
        }

        for(int i=0;i<virtualSensorGrpResult.size();i++){


            VirtualSensorGroup vsGrpObj=new VirtualSensorGroup();
            vsGrpObj.setSensorGroupId(virtualSensorGrpResult.get(i).getSensorGroupId());
            vsGrpObj.setSensorGroupName(virtualSensorGrpResult.get(i).getSensorGroupName());
            vsGrpObj.setSensorGroupDescription(virtualSensorGrpResult.get(i).getSensorGroupDescription());
            vsGrpObj.setVirtualSensorId(virtualSensorGrpResult.get(i).getVirtualSensorId());
            vsGrpObj.setUserEmail(virtualSensorGrpResult.get(i).getUserEmail());

            virtual_sensor_grp_list.add(vsGrpObj);
        }

        return virtual_sensor_grp_list;

    }

    public VirtualSensorGroup findByVirtualSensorGrpId(int sensor_grp_id){

        DynamoDBMapper mapper = new DynamoDBMapper(client);

        Map<String, AttributeValue> filterMap = new HashMap<>();
        filterMap.put(":val1", new AttributeValue().withN(String.valueOf(sensor_grp_id)));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("sensor_group_id=:val1").withExpressionAttributeValues(filterMap);
        virtualSensorGrpResult=mapper.scan(VirtualSensorGroup.class,scanExpression);

        VirtualSensorGroup sensorObj=null;
        if(!(virtualSensorGrpResult.size()==0)) {
            sensorObj = new VirtualSensorGroup();
            sensorObj.setSensorGroupId(virtualSensorGrpResult.get(0).getSensorGroupId());
            sensorObj.setVirtualSensorId(virtualSensorGrpResult.get(0).getVirtualSensorId());
            sensorObj.setUserEmail(virtualSensorGrpResult.get(0).getUserEmail());
            sensorObj.setSensorGroupName(virtualSensorGrpResult.get(0).getSensorGroupName());
            sensorObj.setSensorGroupDescription(virtualSensorGrpResult.get(0).getSensorGroupDescription());

        }

        return sensorObj;
    }

    public void deleteVirtualSensorGroup(int virtual_sensor_grp_id){

        DynamoDBMapper mapper=new DynamoDBMapper(client);
        VirtualSensorGroup virtualsensorgrp=mapper.load(VirtualSensorGroup.class,virtual_sensor_grp_id);
        mapper.delete(virtualsensorgrp);
    }

}
