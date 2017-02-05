/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dougtest.restTest.SchedByStop;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * @author douglasdean
 */
public class Modes {
  private String routeType;
  private String modeName;
  public List<Routes> routeArray = new ArrayList<>();
  
  public void setRouteType(String tmpVal){
    routeType = tmpVal;
  }
  
  public String getRouteType(){
    return routeType;
  }
  
  public void setModeName(String tmpVal){
    modeName = tmpVal;
  }
  
  public String getModeName(){
    return modeName;
  }
  
  public void setRouteArray(List<Routes> tmpArray){
    for(int x=0; x<tmpArray.size();x++){
      routeArray.add(tmpArray.get(x));
    }
  }
  
  public Modes getModeInfo(JsonNode tmpNode){
    Modes newModes = new Modes();
    newModes.setRouteType(tmpNode.path("route_type").asText());
    newModes.setModeName(tmpNode.path("mode_name").asText());
    JsonNode routeNode = tmpNode.path("route");
    for(JsonNode tmpModesNode: routeNode){
      Routes newRoute = new Routes();
      newModes.routeArray.add(newRoute.getRouteInfo(tmpModesNode));
      }
    return newModes;
  }
}
