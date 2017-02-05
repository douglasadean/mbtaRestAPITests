/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dougtest.restTest.stopsbyroute;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author douglasdean
 */
public class directionInfo {
  private int directionId;
  private String directionName;
  public List<stopInfo> lstStops = new ArrayList<>();
  
  public directionInfo getInfo(JsonNode tmpNode){
    directionInfo retInfo = new directionInfo();
    
    retInfo.setDirectionId(tmpNode.path("direction_id").asInt());
    retInfo.setDirectionName(tmpNode.path("direction_name").asText());
    
    JsonNode stopNode = tmpNode.path("stop");
    
    for(JsonNode sNode : stopNode){
      stopInfo stpInf = new stopInfo();
      retInfo.lstStops.add(stpInf.getStopInfo(sNode));
    }
    return retInfo;
  }
  public void setDirectionId(int tmpVal){
    directionId = tmpVal;
  }
  
  public int getDirectionId(){
    return directionId;
  }
  
  public void setDirectionName(String tmpVal){
    directionName = tmpVal;
  }
  
  public String getDirectionName(){
    return directionName;
  }
  
  public void setLstStops(List<stopInfo> tmpVal){
    lstStops = tmpVal;
  }
  
  public int getArraySize(){
    return lstStops.size();
  }
  
}
