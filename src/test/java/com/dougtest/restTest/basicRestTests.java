/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dougtest.restTest;

import com.dougtest.restTest.SchedByRoute.ScheduleByRoute;
import com.dougtest.restTest.SchedByStop.Stop;
import com.dougtest.restTest.stopsbyroute.RouteInfo;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author douglasdean
 * based on code from https://github.com/eugenp/tutorials/tree/master/rest-testing
 * 
 */
public class basicRestTests {
  private static final String badurl = "http://realtime.mbta.com/developer/api/v2/routines?api_key=wX9NwuHnZU2ToO7GmGR9uw&format=json";
  private static final String badurlRequest = "http://realtime.mbta.com/developer/api/v2/stopsbyroute?api_key=wX9NwuHnZU2ToO7GmGR9";
  private static final String badAPIKey = "http://realtime.mbta.com/developer/api/v2/routes?api_key=wX9NwuHnZU2ToO7GmGR9u3&format=json";
  private static final String url = "http://realtime.mbta.com/developer/api/v2/stopsbyroute?api_key=wX9NwuHnZU2ToO7GmGR9uw&format=json&route=";
  private static final String xmlurl = "http://realtime.mbta.com/developer/api/v2/stopsbyroute?api_key=wX9NwuHnZU2ToO7GmGR9uw&format=xml&route=";
  private static final String txturl = "http://realtime.mbta.com/developer/api/v2/stopsbyroute?api_key=wX9NwuHnZU2ToO7GmGR9uw&format=text&route=";
  private static final String urlSchedByStop = "http://realtime.mbta.com/developer/api/v2/schedulebystop?api_key=wX9NwuHnZU2ToO7GmGR9uw&format=json";
  private static final String urlSchedByRoute = "http://realtime.mbta.com/developer/api/v2/schedulebyroute?api_key=wX9NwuHnZU2ToO7GmGR9uw&format=json&route=";
  
  /**
   * This test case is used to verify that if given a bad URL the REST API will return an "Not found error" message 
   * @throws ClientProtocolException
   * @throws IOException 
   */
  @Test
  public void givenUrlDoesNotExist() throws ClientProtocolException, IOException {
    final String jsonMimeType = "application/xml";
    final HttpUriRequest request = new HttpGet(badurl);
    final HttpResponse response = HttpClientBuilder.create().build().execute(request);
    final String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
    assertThat(response.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    assertEquals(jsonMimeType, mimeType);
  }
  
  
  /**
   * This test case is used to verify that if given a bad URL Request the REST API will return a "Bad Request" error message 
   * @throws ClientProtocolException
   * @throws IOException 
   */
  @Test
  public void givenBadURLRequest() throws ClientProtocolException, IOException {
    final String jsonMimeType = "application/xml";
    final HttpUriRequest request = new HttpGet(badurlRequest);
    final HttpResponse response = HttpClientBuilder.create().build().execute(request);
    final String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
    assertThat(response.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));
    assertEquals(jsonMimeType, mimeType);
  }

  
  /**
   * This test case is used to verify that if given a bad URL Request the REST API will return a "Unauthorized" error message 
   * @throws ClientProtocolException
   * @throws IOException 
   */
  @Test
  public void givenAPI_KeyisIncorrect() throws ClientProtocolException, IOException {
    final String jsonMimeType = "application/json";
    final HttpUriRequest request = new HttpGet(badAPIKey);
    final HttpResponse response = HttpClientBuilder.create().build().execute(request);
    final String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
    assertThat(response.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_UNAUTHORIZED));
    assertEquals(jsonMimeType, mimeType);
  }
  
  /**
   * This test will verify that when using a "format=json" the Rest API will the correct MIME Type
   * @throws ClientProtocolException
   * @throws IOException 
   */
  
  @Test
  public void verifyJsonType() throws ClientProtocolException, IOException {
    final String jsonMimeType = "application/json";
    final HttpUriRequest request = new HttpGet(url + "CR-Franklin");

    final HttpResponse response = HttpClientBuilder.create().build().execute(request);

    final String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
    assertEquals(jsonMimeType, mimeType);
  }
  
  /**
   * This test will verify that when using a "format=xml" the Rest API will the correct MIME Type
   * @throws ClientProtocolException
   * @throws IOException 
   */
  
  @Test
  public void verifyXMLType() throws ClientProtocolException, IOException {
    final String jsonMimeType = "application/xml";
    final HttpUriRequest request = new HttpGet(xmlurl + "CR-Franklin");

    final HttpResponse response = HttpClientBuilder.create().build().execute(request);

    final String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
    assertEquals(jsonMimeType, mimeType);
  }
  
  /**
   * This test will verify that when using a "format=text" the Rest API will the correct MIME Type There is a bug in this and should be logged. This test fails 
   * @throws ClientProtocolException
   * @throws IOException 
   */
  
  @Test
  public void verifyBadType() throws ClientProtocolException, IOException {
    final String jsonMimeType = "application/xml";
    final HttpUriRequest request = new HttpGet(txturl + "CR-Franklin");

    final HttpResponse response = HttpClientBuilder.create().build().execute(request);

    final String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
    assertEquals(jsonMimeType, mimeType);
  }
  
  /**
   * Used to verify that the API will give the correct response for a bad "SchedStop name" in this case I used "CR-Moon" instead of a valid name.
   * @throws ClientProtocolException
   * @throws IOException 
   */
  
  @Test
  public void verifyBadRoute() throws ClientProtocolException, IOException {
    final String jsonMimeType = "application/json";
    final HttpUriRequest request = new HttpGet(url + "CR-Moon");

    final HttpResponse response = HttpClientBuilder.create().build().execute(request);
    final String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
    assertThat(response.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
    assertEquals(jsonMimeType, mimeType);
  }
  
  
  /**
   * This takes the a "Gold Standard" for what the REST API should return for CR-Franklin. The expected values are stored in the "test-cases" folder (CR_Franklin). 
   * @throws ClientProtocolException
   * @throws IOException 
   */
  @Test
  public void verifyGoldStandardFranklin() throws ClientProtocolException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    final HttpUriRequest request = new HttpGet(url + "CR-Franklin");
    final HttpResponse response = HttpClientBuilder.create().build().execute(request);
    final JsonNode resource = getJsonInfo.retrieveResourceFromResponse(response);
    RouteInfo route = new RouteInfo(resource);
   
    JsonNode testNodeData = mapper.readTree(new File("test-cases/CR-Franklin.json"));
    RouteInfo testRoute = new RouteInfo(testNodeData);
    
    assertEquals(route.getArraySize(), testRoute.getArraySize());
    
    testRouteInfo(route, testRoute);
  }
  
  
   /**
   * This verifies that the REST API is not case sensitive for the Routes name.  Used 'cr-franklin' instead of 'CR-Franklin'
   * @throws ClientProtocolException
   * @throws IOException 
   */
  @Test
  public void verifyGoldStandardFranklinLower() throws ClientProtocolException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    final HttpUriRequest request = new HttpGet(url + "cr-franklin");
    final HttpResponse response = HttpClientBuilder.create().build().execute(request);
    final JsonNode resource = getJsonInfo.retrieveResourceFromResponse(response);
    RouteInfo route = new RouteInfo(resource);
    JsonNode testNodeData = mapper.readTree(new File("test-cases/CR-Franklin.json"));
    RouteInfo testRoute = new RouteInfo(testNodeData);
    
    assertEquals(route.getArraySize(), testRoute.getArraySize());
    
    testRouteInfo(route, testRoute);
  }
  
  
   /**
   * This takes the a "Gold Standard" for what the REST API should return for Green-B. The expected values are stored in the "test-cases" folder. (Green-B.json). 
   * @throws ClientProtocolException
   * @throws IOException 
   */
  @Test
  public void verifyGoldStandardGreenB() throws ClientProtocolException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    final HttpUriRequest request = new HttpGet(url + "Green-B");
    final HttpResponse response = HttpClientBuilder.create().build().execute(request);
    final JsonNode resource = getJsonInfo.retrieveResourceFromResponse(response);
    RouteInfo route = new RouteInfo(resource);
    
    JsonNode testNodeData = mapper.readTree(new File("test-cases/Green-B.json"));
    RouteInfo testRoute = new RouteInfo(testNodeData);
    
    assertEquals(route.getArraySize(), testRoute.getArraySize());
    testRouteInfo(route, testRoute);
  }
  
  
   /**
   * This this test cases verifies the test mechanism works.  I changed a value in the file Green-B-bad.json to verify that the algorithm will find discrepancies  
   * @throws ClientProtocolException
   * @throws IOException 
   */
  @Test
  public void verifyGoldStandardGreenBBad() throws ClientProtocolException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    final HttpUriRequest request = new HttpGet(url + "Green-B");
    final HttpResponse response = HttpClientBuilder.create().build().execute(request);
    final JsonNode resource = getJsonInfo.retrieveResourceFromResponse(response);
    RouteInfo route = new RouteInfo(resource);
    
    JsonNode testNodeData = mapper.readTree(new File("test-cases/Green-B-bad.json"));
    RouteInfo testRoute = new RouteInfo(testNodeData);
    
    assertEquals(route.getArraySize(), testRoute.getArraySize());
    
    testRouteInfo(route, testRoute);
  }
  
  
  /**
   * This takes the a "Gold Standard" for what the REST API should return for 38. The expected values are stored in the "test-cases" folder. (route38.json). 
   * @throws ClientProtocolException
   * @throws IOException 
   */
  @Test
  public void verifyGoldStandardRoute38() throws ClientProtocolException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    final HttpUriRequest request = new HttpGet(url + "38");
    final HttpResponse response = HttpClientBuilder.create().build().execute(request);
    final JsonNode resource = getJsonInfo.retrieveResourceFromResponse(response);
    RouteInfo route = new RouteInfo(resource);
    
    JsonNode testNodeData = mapper.readTree(new File("test-cases/Route38.json"));
    RouteInfo testRoute = new RouteInfo(testNodeData);
    
    assertEquals(route.getArraySize(), testRoute.getArraySize());
    testRouteInfo(route, testRoute);
    
  }
  
  
   /**
   * This takes the a "Gold Standard" for what the REST API should return for Boat-F1. The expected values are stored in the "test-cases" folder. (Boat-F1.json). 
   * @throws ClientProtocolException
   * @throws IOException 
   */
  @Test
  public void verifyGoldStandardBoatF1() throws ClientProtocolException, IOException {
    ObjectMapper mapper = new ObjectMapper();
    final HttpUriRequest request = new HttpGet(url + "Boat-F1");
    final HttpResponse response = HttpClientBuilder.create().build().execute(request);
    final JsonNode resource = getJsonInfo.retrieveResourceFromResponse(response);
    RouteInfo route = new RouteInfo(resource);
    
    JsonNode testNodeData = mapper.readTree(new File("test-cases/Boat-F1.json"));
    RouteInfo testRoute = new RouteInfo(testNodeData);
    
    assertEquals(route.getArraySize(), testRoute.getArraySize());
    testRouteInfo(route, testRoute);
  }
  
  /**
   * I used this test case to verify from the STOPSBYROUTE query and got a list of the stops.  Then I pulled the 12 stop (Walpole) and then used the query 
   * SCHEDULEBYSTOP to find when the next train was scheduled to enter the station for an outboard train.  Based on that I verified that the SCHEDULEBYROUTE
   * contained the same stop and time for a train approaching the station.
   * @throws ClientProtocolException
   * @throws IOException 
   */
  
  @Test
  public void IntegrationTestStationStopTimes() throws ClientProtocolException, IOException{
    ObjectMapper mapper = new ObjectMapper();
    
    final HttpUriRequest request = new HttpGet(url + "CR-Franklin");
    final HttpResponse response = HttpClientBuilder.create().build().execute(request);
    final JsonNode resource = getJsonInfo.retrieveResourceFromResponse(response);
    RouteInfo route = new RouteInfo(resource);
   
    HttpUriRequest requestStop = new HttpGet(urlSchedByStop + "&stop=" + route.lstDirections.get(0).lstStops.get(12).getStopId() + "&route=Cr-Franklin&direction=0");
    HttpResponse rspStop = HttpClientBuilder.create().build().execute(requestStop);
    JsonNode resourceStop = getJsonInfo.retrieveResourceFromResponse(rspStop);
    
    Stop schedStop = new Stop(resourceStop);
    
    assertEquals(route.lstDirections.get(0).lstStops.get(12).getStopId(), schedStop.getStopId());
    assertEquals(route.lstDirections.get(0).lstStops.get(12).getStopName(), schedStop.getStopName());
    
    HttpUriRequest requestSchedByRoute = new HttpGet(urlSchedByRoute + "CR-Franklin&direction=0");
    HttpResponse rspSchedByRoute = HttpClientBuilder.create().build().execute(requestSchedByRoute);
    JsonNode resourceSchedbyRoute = getJsonInfo.retrieveResourceFromResponse(rspSchedByRoute);
    
    ScheduleByRoute schedByRoute = new ScheduleByRoute();
    schedByRoute.setScheduleByRoute(resourceSchedbyRoute);
    int schedByRtetrip = 0;
    int schedByRteDir = 0;
    int schedByRteStop = 0;
    
    outerloop:
    for(int i=0; i<schedByRoute.dirArray.size(); i++){
      for(int x=0; x<schedByRoute.dirArray.get(i).tripArray.size(); x++){
        String schedTripID = schedStop.modesArray.get(0).routeArray.get(0).dirArray.get(0).tripArray.get(0).getTripId();
        if(schedByRoute.dirArray.get(i).tripArray.get(x).getTripId().equals(schedTripID)){
          schedByRtetrip = x;
          schedByRteDir = i;
          for(int y=0; y<schedByRoute.dirArray.get(i).tripArray.get(x).schedStopArray.size(); y++){
            if(schedByRoute.dirArray.get(i).tripArray.get(x).schedStopArray.get(y).getStopId().equals(schedStop.getStopId())){
              schedByRteStop=y;
              break outerloop;
            }
            
          }
          
        }
      }
    }
    assertEquals(schedByRoute.dirArray.get(schedByRteDir).tripArray.get(schedByRtetrip).schedStopArray.get(schedByRteStop).getSchArrDt(), schedStop.modesArray.get(0).routeArray.get(0).dirArray.get(0).tripArray.get(0).getSchedArrDate());
    assertEquals(schedByRoute.dirArray.get(schedByRteDir).tripArray.get(schedByRtetrip).schedStopArray.get(schedByRteStop).getSchDepDt(), schedStop.modesArray.get(0).routeArray.get(0).dirArray.get(0).tripArray.get(0).getSchedDepartDate());
  }
  
  /**
   * This will test the two Routes information against each of the fields.
   * @param liveTest
   * @param goldTest 
   */
  void testRouteInfo(RouteInfo liveTest, RouteInfo goldTest){
    for(int x =0; x<liveTest.getArraySize(); x++){
      assertEquals(liveTest.lstDirections.get(x).getDirectionId(), goldTest.lstDirections.get(x).getDirectionId());
      assertEquals(liveTest.lstDirections.get(x).getDirectionName(), goldTest.lstDirections.get(x).getDirectionName());
      assertEquals(liveTest.lstDirections.get(x).lstStops.size(), goldTest.lstDirections.get(x).lstStops.size());
      for(int y=0; y<liveTest.lstDirections.get(x).lstStops.size(); y++){
        assertEquals(liveTest.lstDirections.get(x).lstStops.get(y).getStopOrder(), goldTest.lstDirections.get(x).lstStops.get(y).getStopOrder());
        assertEquals(liveTest.lstDirections.get(x).lstStops.get(y).getStopName(), goldTest.lstDirections.get(x).lstStops.get(y).getStopName());
        assertEquals(liveTest.lstDirections.get(x).lstStops.get(y).getStopId(), goldTest.lstDirections.get(x).lstStops.get(y).getStopId());
        assertEquals(liveTest.lstDirections.get(x).lstStops.get(y).getParenStation(), goldTest.lstDirections.get(x).lstStops.get(y).getParenStation());
        assertEquals(liveTest.lstDirections.get(x).lstStops.get(y).getParenStationName(), goldTest.lstDirections.get(x).lstStops.get(y).getParenStationName());
        assertEquals(liveTest.lstDirections.get(x).lstStops.get(y).getStopLat(), goldTest.lstDirections.get(x).lstStops.get(y).getStopLat());
        assertEquals(liveTest.lstDirections.get(x).lstStops.get(y).getstopLon(), goldTest.lstDirections.get(x).lstStops.get(y).getstopLon());
      }
    }
  }
}
