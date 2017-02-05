/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dougtest.restTest.stopsbyroute;

import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * @author douglasdean
 */
public class stopInfo {
  private int stopOrder;
  private String stopID;
  private String stopName;
  private String parentStation;
  private String parentStationName;
  private String stopLat;
  private String stopLon;
  
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
  
  public void setStopOrder(int tmpVal){
    stopOrder = tmpVal;
  }
  
  public int getStopOrder(){
    return stopOrder;
  }
  
  public void setStopId(String tmpVal){
    stopID = tmpVal;
  }
  
  public String getStopId(){
    return stopID;
  }
  
  public void setStopName(String tmpVal){
    stopName = tmpVal;
  }
  
  public String getStopName(){
    return stopName;
  }
  
  public void setParentStation(String tmpVal){
    parentStation = tmpVal;
  }
  
  public String getParenStation(){
    return parentStation;
  }
  
  public void setParentStationName(String tmpVal){
    parentStationName = tmpVal;
  }
  
  public String getParenStationName(){
    return parentStationName;
  }
  
  public void setStopLat(String tmpVal){
    stopLat = tmpVal;
  }
  
  public String getStopLat(){
    return stopLat;
  }
  
  public void setStopLon(String tmpVal){
    stopLon = tmpVal;
  }
  
  public String getstopLon(){
    return stopLon;
  }
}
