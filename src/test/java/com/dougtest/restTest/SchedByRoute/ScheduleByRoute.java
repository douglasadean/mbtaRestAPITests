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
public class ScheduleByRoute {
  private int routeId;
  private String routeName;
  public List<ScheduleDirection> dirArray = new ArrayList<ScheduleDirection>();
  
  
  public void setScheduleByRoute (JsonNode tmpNode){
    //ScheduleByRoute newRoute = new ScheduleByRoute();
    this.setRouteId(tmpNode.path("route_id").asInt());
    this.setRouteName(tmpNode.path("route_name").asText());
    JsonNode dirNode = tmpNode.path("direction");
    for(JsonNode tmpDirNode : dirNode){
      ScheduleDirection newDirection = new ScheduleDirection();
      this.dirArray.add(newDirection.getDirectionInfo(tmpDirNode));
    }
    
  }
  
  public void setRouteId(int tmpVal){
    routeId = tmpVal;
  }
  
  public int getRouteId(){
    return routeId;
  }
  
  public void setRouteName(String tmpVal){
    routeName = tmpVal;
  }
  
  public String getRouteName(){
    return routeName;
  }
  
  public void setDirArray(List<ScheduleDirection> tmpArray){
    for(int x=0; x<tmpArray.size();x++){
      dirArray.add(tmpArray.get(x));
    }
  }
}
