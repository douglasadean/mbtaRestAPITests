/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dougtest.restTest.SchedByStop;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author douglasdean
 */
public class Stop {
  
  private String stopId;
  private String stopName;
  public List<Modes> modesArray = new ArrayList<>();
  
  public Stop(JsonNode resource){
    
    stopId = resource.path("stop_id").asText();
    stopName = resource.path("stop_name").asText();
    JsonNode modesNode = resource.path("mode");
    for(JsonNode tmpNode : modesNode){
      Modes tmpModes = new Modes();
      this.modesArray.add(tmpModes.getModeInfo(tmpNode));
    }
    
  }
  
  public void setStopId(String tmpVal){
    stopId = tmpVal;
  }
  
  public String getStopId(){
    return stopId;
  }
  
  public void setStopName(String tmpVal){
    stopName = tmpVal;
  }
  
  public String getStopName(){
    return stopName;
  }
  
  public void setModesArray(List<Modes> tmpArray){
    for(int x=0; x<tmpArray.size();x++){
      modesArray.add(tmpArray.get(x));
    }
  }
}
