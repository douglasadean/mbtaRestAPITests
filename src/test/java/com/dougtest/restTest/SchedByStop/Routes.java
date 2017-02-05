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
public class Routes {
  private String routeId;
  private String routeName;
  public List<Direction> dirArray = new ArrayList<>();
  
  public void setRouteId(String tmpVal){
    routeId = tmpVal;
  }
  
  public String getRouteId(){
    return routeId;
  }
  
  public void setRouteName(String tmpVal){
    routeName = tmpVal;
  }
  
  public String getRouteName(){
    return routeName;
  }
  
  public void setDirArray(List<Direction> tmpArray){
    for(int x=0; x<tmpArray.size();x++){
      dirArray.add(tmpArray.get(x));
    }
  }
  
  public Routes getRouteInfo (JsonNode tmpNode){
    Routes retRoute = new Routes();
    retRoute.setRouteId(tmpNode.path("route_id").asText());
    retRoute.setRouteName(tmpNode.path("route_name").asText());
    JsonNode dirNode = tmpNode.path("direction");
    for(JsonNode tmpModesNode: dirNode){
        Direction newDirection = new Direction();
        retRoute.dirArray.add(newDirection.getDirectionInfo(tmpModesNode));
      }
    return retRoute;
  }
}
