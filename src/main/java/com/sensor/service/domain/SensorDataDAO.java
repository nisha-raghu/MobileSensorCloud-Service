package com.sensor.service.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by nisha on 11/20/16.
 */
public class SensorDataDAO {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    private Hashtable<Date,Integer> sensor_data = new Hashtable<>();

    public SensorDataDAO(){
        try {
            sensor_data.put(formatter.parse("2016-11-19T06:00:00-0800"), 57);
            sensor_data.put(formatter.parse("2016-11-19T07:00:00-0800"), 56);
            sensor_data.put(formatter.parse("2016-11-19T08:00:00-0800"), 56);
            sensor_data.put(formatter.parse("2016-11-19T09:00:00-0800"), 59);
            sensor_data.put(formatter.parse("2016-11-19T10:00:00-0800"), 61);
            sensor_data.put(formatter.parse("2016-11-19T11:00:00-0800"), 63);
            sensor_data.put(formatter.parse("2016-11-19T12:00:00-0800"), 63);
            sensor_data.put(formatter.parse("2016-11-19T13:00:00-0800"), 64);
            sensor_data.put(formatter.parse("2016-11-19T14:00:00-0800"), 61);
            sensor_data.put(formatter.parse("2016-11-19T15:00:00-0800"), 58);
            sensor_data.put(formatter.parse("2016-11-19T16:00:00-0800"), 56);
            sensor_data.put(formatter.parse("2016-11-19T17:00:00-0800"), 56);
            sensor_data.put(formatter.parse("2016-11-19T18:00:00-0800"), 56);
            sensor_data.put(formatter.parse("2016-11-19T19:00:00-0800"), 57);
            sensor_data.put(formatter.parse("2016-11-19T20:00:00-0800"), 57);
        }catch(ParseException e){
            System.out.println(e);
        }

    }

    public Hashtable<Date,Integer> sensorDataList(Integer sensor_id){
        return sensor_data;
    }
}
