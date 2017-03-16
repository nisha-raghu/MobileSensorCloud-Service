package com.sensor.service.domain;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.sensor.service.model.ApiConstants;
import com.sensor.service.model.Plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nisha on 11/15/16.
 */

public class PlanDAO {

    private List<Plan> plan_list=new ArrayList<>();
    private List<Plan> planResult=new ArrayList<>();
    static AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider(ApiConstants.path,ApiConstants.profile)).withRegion(Regions.US_WEST_2);;

    public List planList(Integer planId){

        try {

            DynamoDBMapper mapper = new DynamoDBMapper(client);

            if(!(planId==0)) {
                Map<String, AttributeValue> filterMap = new HashMap<>();
                filterMap.put(":val1", new AttributeValue().withN(String.valueOf(planId)));

                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                        .withFilterExpression("plan_id=:val1").withExpressionAttributeValues(filterMap);
                planResult=mapper.scan(Plan.class,scanExpression);

            }else{
                DynamoDBScanExpression scanExpression = new DynamoDBScanExpression().withLimit(50);
                planResult=mapper.scan(Plan.class,scanExpression);
            }


            for(int i=0;i<planResult.size();i++){
                Plan planObj=new Plan();
                planObj.setPlanId(planResult.get(i).getPlanId());
                planObj.setPlanName(planResult.get(i).getPlanName());
                planObj.setPlanPrice(planResult.get(i).getPlanPrice());
                plan_list.add(planObj);

            }

        } catch (Throwable t) {
            t.printStackTrace();
        }

        return plan_list;
    }

}
