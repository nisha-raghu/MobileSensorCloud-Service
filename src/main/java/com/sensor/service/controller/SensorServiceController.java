package com.sensor.service.controller;

import com.sensor.service.domain.*;
import com.sensor.service.model.*;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.omg.CORBA.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by nisha on 11/12/16.
 */

@CrossOrigin
@RestController
public class SensorServiceController {


    @RequestMapping(value="/vendors",method= RequestMethod.GET)
    public List vendor(@RequestParam(value = "vendor_email",required = false,defaultValue = "0")String vendor_email){

        VendorDAO vendorDAO = new VendorDAO();
        List<Vendor> vendorList=vendorDAO.vendorList(vendor_email);
        return vendorList;

    }

    @RequestMapping(value="/users",method=RequestMethod.GET)
    public List users(@RequestParam(value = "user_email",required = false,defaultValue = "0") String user_email){

        UserDAO userDAO=new UserDAO();
        List<Users> usersList=userDAO.userList(user_email);
        return usersList;

    }

    @RequestMapping(value="/sensors",method=RequestMethod.GET)
    public List sensors(@RequestParam(value = "vendor_email", required = false,defaultValue = "0") String vendor_email){
        SensorDAO sensorDAO=new SensorDAO();
        List<Sensor> sensorList=sensorDAO.sensorList(vendor_email);
        return sensorList;
    }


    @RequestMapping(value="/plans",method=RequestMethod.GET)
    public List user_plans(@RequestParam(value = "plan_id", required = false,defaultValue = "0") Integer plan_id){

        PlanDAO planDAO=new PlanDAO();
        List<Plan> planList=planDAO.planList(plan_id);
        return planList;

    }

    @RequestMapping(value = "/vendors",method=RequestMethod.POST)
    public ResponseEntity<Vendor> vendor_save(@RequestBody Vendor vendor){

        VendorDAO vendorDAO=new VendorDAO();
        vendorDAO.createVendor(vendor);

        return new ResponseEntity<Vendor>(vendor,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users",method=RequestMethod.POST)
    public ResponseEntity<Users> user_save(@RequestBody Users user){

        UserDAO userDAO=new UserDAO();
        userDAO.createUser(user);

        return new ResponseEntity<Users>(user,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/sensors",method=RequestMethod.POST)
    public ResponseEntity<Sensor> sensor_save(@RequestBody Sensor sensor){

        SensorDAO sensorDAO=new SensorDAO();
        sensorDAO.createSensor(sensor);

        return new ResponseEntity<Sensor>(sensor,HttpStatus.CREATED);
    }

    @RequestMapping(value="/virtualsensor",method=RequestMethod.POST)
    public ResponseEntity<VirtualSensor> createVirtualSensor(@RequestBody VirtualSensor virtualSensor){

        VirtualSensorDAO virtualSensorDAO=new VirtualSensorDAO();
        virtualSensorDAO.createSensor(virtualSensor);
        return new ResponseEntity<VirtualSensor>(virtualSensor,HttpStatus.CREATED);

    }

    @RequestMapping(value = "/virtualsensor",method=RequestMethod.GET)
    public List virtualsensors(@RequestParam(value = "user_email", required = false,defaultValue = "0") String user_email){
        VirtualSensorDAO virtualsensorDAO=new VirtualSensorDAO();
        List<Sensor> sensorList=virtualsensorDAO.virtualSensorList(user_email);
        return sensorList;
    }

    @RequestMapping(value="/virtualSensorGroup",method=RequestMethod.POST)
    public ResponseEntity<VirtualSensorGroup> createVirtualSensor(@RequestBody VirtualSensorGroup virtualSensorGrp){

        VirtualSensorGroupDAO virtualSensorGrpDAO=new VirtualSensorGroupDAO();
        virtualSensorGrpDAO.createSensorGroup(virtualSensorGrp);
        return new ResponseEntity<VirtualSensorGroup>(virtualSensorGrp,HttpStatus.CREATED);

    }

    @RequestMapping(value="/virtualSensorGroup/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<VirtualSensorGroup> deleteVirtualSensorGroup(@PathVariable("id") int id){
        VirtualSensorGroupDAO virtualSensorGroupDAO=new VirtualSensorGroupDAO();
        virtualSensorGroupDAO.deleteVirtualSensorGroup(id);
        VirtualSensorGroup virtualSensorGrp=virtualSensorGroupDAO.findByVirtualSensorGrpId(id);
        if(virtualSensorGrp==null){
            return new ResponseEntity<VirtualSensorGroup>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<VirtualSensorGroup>(virtualSensorGrp,HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/virtualSensorGroup",method=RequestMethod.GET)
    public List virtualsensorsgroup(@RequestParam(value = "user_email", required = false,defaultValue = "0") String user_email){
        VirtualSensorGroupDAO virtualsensorDAO=new VirtualSensorGroupDAO();
        List<VirtualSensorGroup> grpList=virtualsensorDAO.virtualSensorGroupList(user_email);
        return grpList;
    }


    @RequestMapping(value="/users",method = RequestMethod.PUT)
    public ResponseEntity<Users> updateUser(@RequestBody Users users){
        UserDAO userDAO=new UserDAO();
        Users newUser=new Users();

        newUser.setUserEmail(users.getUserEmail());
        newUser.setUserName(users.getUserName());
        newUser.setUserPassword(users.getUserPassword());
        newUser.setPlanId(users.getPlanId());

        userDAO.updateUser(newUser);
        return new ResponseEntity<Users>(newUser,HttpStatus.OK);
    }

    @RequestMapping(value="/sensors/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Sensor> updateSensor(@PathVariable("id") int id,@RequestBody Sensor sensor){

        SensorDAO sensorDAO=new SensorDAO();
        Sensor newSensor=sensorDAO.findBySensorId(id);
        if(newSensor==null){
            return new ResponseEntity<Sensor>(HttpStatus.NOT_FOUND);
        }

        newSensor.setSensorId(id);
        newSensor.setSensorName(sensor.getSensorName());
        newSensor.setSensorType(sensor.getSensorType());
        newSensor.setSensorLocation(sensor.getSensorLocation());
        newSensor.setSensorStatus(sensor.getSensorStatus());
        newSensor.setUserEmail(sensor.getUserEmail());
        newSensor.setVendorEmail(sensor.getVendorEmail());

        sensorDAO.updateSensor(newSensor);
        return new ResponseEntity<Sensor>(newSensor,HttpStatus.OK);

    }

    @RequestMapping(value="/sensors/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Sensor> deleteUser(@PathVariable("id") int id){
        SensorDAO sensorDAO=new SensorDAO();
        Sensor delSensor=sensorDAO.findBySensorId(id);
        if(delSensor==null){
            return new ResponseEntity<Sensor>(HttpStatus.NOT_FOUND);
        }

        sensorDAO.deleteSensor(id);
        return new ResponseEntity<Sensor>(delSensor,HttpStatus.NO_CONTENT);

    }

    @RequestMapping(value = "/virtualsensor/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<VirtualSensor> deleteVirtualSensor(@PathVariable("id") int id){
        VirtualSensorDAO virtualSensorDAO=new VirtualSensorDAO();
        VirtualSensor virtualSensor=virtualSensorDAO.findByVirtualSensorId(id);
        if(virtualSensor==null){
            return new ResponseEntity<VirtualSensor>(HttpStatus.NOT_FOUND);
        }

        virtualSensorDAO.deleteVirtualSensor(id);
        return new ResponseEntity<VirtualSensor>(virtualSensor,HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/sensor_data/{id}", method=RequestMethod.GET)
    public ResponseEntity<SensorDataResponse> getAllSensorData(@PathVariable("id") int id){

        SensorDataDAO sd = new SensorDataDAO();
        Hashtable<Date, Integer> sensorData =  sd.sensorDataList(1);

        if (sensorData==null)
            return new ResponseEntity<SensorDataResponse>(HttpStatus.NOT_FOUND);

        SensorDataResponse ssr = new SensorDataResponse();
        ssr.setSensorData(sensorData);
        ssr.setSensorDataPoint("Temperature");
        return new ResponseEntity<SensorDataResponse>(ssr, HttpStatus.OK);
    }

}
