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
public class SchedStop {
  private int stopSequence;
  private String stopId;
  private String stopName;
  private String schArrDt;
  private String schDepDt;
  
  public void setStopSequence(int tmpVal){
    stopSequence = tmpVal;
  }
  
  public int getStopSequence(){
    return stopSequence;
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
  
  public void setSchArrDt(String tmpVal){
    schArrDt = tmpVal;
  }
  
  public String getSchArrDt(){
    return schArrDt;
  }
  
  public void setSchDepDt(String tmpVal){
    schDepDt = tmpVal;
  }
  
  public String getSchDepDt(){
    return schDepDt;
  }
  
  public SchedStop getStopInfo(JsonNode stopNode){
    SchedStop tmpStop = new SchedStop();
    tmpStop.setStopSequence(stopNode.path("stop_sequence").asInt());
    tmpStop.setStopId(stopNode.path("stop_id").asText());
    tmpStop.setStopName(stopNode.path("stop_name").asText());
    tmpStop.setSchArrDt(stopNode.path("sch_arr_dt").asText());
    tmpStop.setSchDepDt(stopNode.path("sch_dep_dt").asText());
    return tmpStop;
  }
}
