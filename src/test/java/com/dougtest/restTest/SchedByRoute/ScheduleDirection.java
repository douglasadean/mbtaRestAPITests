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
public class ScheduleDirection {
  
  private String directionId;
  private String directionName;
  public List<ScheduleTrip> tripArray = new ArrayList<ScheduleTrip>();
  
  public void setDirectionId(String tmpVal){
    directionId = tmpVal;
  }
  
  public String getDirectionId(){
    return directionId;
  }
  
  public void setDirectionName(String tmpVal){
    directionName = tmpVal;
  }
  
  public String getDirectionName(){
    return directionName;
  }
  
  public void setTripArray(List<ScheduleTrip> tmpArray){
    for(int x=0; x<tmpArray.size();x++){
      tripArray.add(tmpArray.get(x));
    }
  }
  
  public ScheduleDirection getDirectionInfo(JsonNode tmpNode){
    ScheduleDirection newDirection = new ScheduleDirection();
    newDirection.setDirectionId(tmpNode.path("direction_id").asText());
    newDirection.setDirectionName(tmpNode.path( "direction_name").asText());
    JsonNode tripNode = tmpNode.path("trip");
    
    for(JsonNode tmpModesNode: tripNode){
      ScheduleTrip newTrip = new ScheduleTrip();
      newDirection.tripArray.add(newTrip.getTripInfo(tmpModesNode));
    }
    return newDirection;
  }
}
