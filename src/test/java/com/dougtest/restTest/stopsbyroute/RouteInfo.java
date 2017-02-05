/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dougtest.restTest.stopsbyroute;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;
/**
 *
 * @author douglasdean
 */
public class RouteInfo {
  public List<directionInfo> lstDirections = new ArrayList<>();

  public RouteInfo(JsonNode resource) {
    JsonNode directionNode = resource.path("direction");
    
    for (JsonNode node : directionNode) {
      directionInfo dInfo = new directionInfo();
      lstDirections.add(dInfo.getInfo(node)); 
    } 
  
  }
 
  private directionInfo getValues(JsonNode tmpNode){
    directionInfo dInfo = new directionInfo();
    List<stopInfo> stopArray = new ArrayList<>();
    
    dInfo.setDirectionId(tmpNode.path("direction_id").asInt());
    dInfo.setDirectionName(tmpNode.path("direction_name").asText());
    
    JsonNode stopNode = tmpNode.path("stop");
    for(JsonNode sNode : stopNode){
      stopArray.add(getStopInfo(sNode));
    }
    dInfo.setLstStops(stopArray);
    
    return dInfo;  
  }
  public stopInfo getStopInfo(JsonNode tmpNode){
    stopInfo returnInfo = new stopInfo();
    
    returnInfo.setStopOrder(tmpNode.path("stop_order").asInt());
    returnInfo.setStopId(tmpNode.path("stop_id").asText());
    returnInfo.setStopName(tmpNode.path("stop_name").asText());
    returnInfo.setParentStation(tmpNode.path("parent_station").asText());
    returnInfo.setParentStationName(tmpNode.path("parent_station_name").asText());
    returnInfo.setStopLat(tmpNode.path("stop_lat").asText());
    returnInfo.setStopLon(tmpNode.path("stop_lon").asText());
    
    return returnInfo;
  }
  
  public int getArraySize(){
    return lstDirections.size();
  }
  
}
