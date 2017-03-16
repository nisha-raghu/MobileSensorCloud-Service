package com.sensor.service.domain;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.sensor.service.model.ApiConstants;
import com.sensor.service.model.Sensor;
import com.sensor.service.model.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nisha on 11/12/16.
 */


public class UserDAO {

    public List<Users> user_list=new ArrayList<>();
    List<Users> userResult=new ArrayList<>();
    static AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider(ApiConstants.path,ApiConstants.profile)).withRegion(Regions.US_WEST_2);

    public List userList(String userEmail){

        try {

            DynamoDBMapper mapper = new DynamoDBMapper(client);

            if(!(userEmail.equals("0"))) {

                Map<String, AttributeValue> filterMap = new HashMap<>();
                filterMap.put(":val1", new AttributeValue().withS(userEmail));

                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                        .withFilterExpression("user_email=:val1").withExpressionAttributeValues(filterMap);
                userResult=mapper.scan(Users.class,scanExpression);

            }else {
                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withLimit(50);
                userResult = mapper.scan(Users.class, scanExpression);
            }

            for(int i=0;i<userResult.size();i++){
                Users userObj=new Users();

                userObj.setUserName(userResult.get(i).getUserName());
                userObj.setUserEmail(userResult.get(i).getUserEmail());
                userObj.setUserPassword(userResult.get(i).getUserPassword());
                userObj.setPlanId(userResult.get(i).getPlanId());
                user_list.add(userObj);

            }

        } catch (Throwable t) {
            t.printStackTrace();
        }

        return user_list;
    }

    public void createUser(Users user){

        Users newUserItem=new Users();

        DynamoDBMapper mapper=new DynamoDBMapper(client);

        newUserItem.setUserName(user.getUserName());
        newUserItem.setUserEmail(user.getUserEmail());
        newUserItem.setUserPassword(user.getUserPassword());
        newUserItem.setPlanId(user.getPlanId());

        mapper.save(newUserItem);

    }

    public void updateUser(Users users){

        DynamoDBMapper mapper = new DynamoDBMapper(client);
        mapper.save(users);

    }


}

