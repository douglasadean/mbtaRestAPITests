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
public class Trip {
  private String tripId;
  private String tripName;
  private String schArrDt;
  private String schDepDt;
  
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
  
  public void setSchedArrDate(String tmpVal){
    schArrDt = tmpVal;
  }
  
  public String getSchedArrDate(){
    return schArrDt;
  }
  
  public void setSchedDepartDate(String tmpVal){
    schDepDt = tmpVal;
  }
  
  public String getSchedDepartDate(){
    return schDepDt;
  }
  
  public Trip getTripInfo(JsonNode tmpNode){
    Trip newTrip = new Trip();
    newTrip.setTripId(tmpNode.path("trip_id").asText());
    newTrip.setTripName(tmpNode.path( "trip_name").asText());
    newTrip.setSchedArrDate(tmpNode.path("sch_arr_dt").asText());
    newTrip.setSchedDepartDate(tmpNode.path( "sch_dep_dt").asText());
    
    return newTrip;
  }
  
}
