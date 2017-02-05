/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dougtest.restTest;


import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * @author douglasdean
 * Based on the code from https://github.com/eugenp/tutorials/tree/master/rest-testing
 */
public class getJsonInfo {
  public static JsonNode retrieveResourceFromResponse(final HttpResponse response) throws IOException {
    final String jsonFromResponse = EntityUtils.toString(response.getEntity());
    final ObjectMapper mapper = new ObjectMapper();
    JsonNode root = mapper.readTree(jsonFromResponse);
    return root;
  }

}
