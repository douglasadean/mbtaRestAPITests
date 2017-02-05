/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dougtest.restTest.SchedByRoute;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author douglasdean
 */
public class ScheduleTrip {
  private String tripId;
  private String tripName;
  public List<SchedStop> schedStopArray = new ArrayList();
  
  public void setTripId(String tmpVal){
    tripId = tmpVal;
  }
  
  public String getTripId(){
    return tripId;
  }
  
  public void setTripName(String tmpVal){
    tripName = tmpVal;
  }
  
  public String getTripName(){
    return tripName;
  }
  
  public void setStopArray(List<SchedStop> tmpArray){
    for (SchedStop tmpArray1 : tmpArray) {
      schedStopArray.add(tmpArray1);
    }
  }
  
  public ScheduleTrip getTripInfo (JsonNode tmpNode){
    ScheduleTrip newTrip = new ScheduleTrip();
    newTrip.setTripId(tmpNode.path("trip_id").asText());
    newTrip.setTripName(tmpNode.path( "trip_name").asText());
    JsonNode stopNode = tmpNode.path("stop");
    for(JsonNode tmpModesNode : stopNode){
      SchedStop newStop = new SchedStop();
      newTrip.schedStopArray.add(newStop.getStopInfo(tmpModesNode));
    }
    return newTrip;
  }
}
