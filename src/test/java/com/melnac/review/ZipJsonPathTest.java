package com.melnac.review;

import com.melnac.utilities.ZipBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZipJsonPathTest extends ZipBase {
   @Test
   public void test(){

       Response response = given().accept(ContentType.JSON).and().
               pathParam("state", "ma").
               pathParam("city", "belmont").
               when().get("/{state}/{city}");
       response.prettyPrint();
       assertEquals(200, response.statusCode());
       assertEquals("application/json", response.contentType());
       assertTrue(response.header("Server").equalsIgnoreCase("cloudflare"));
       assertTrue(response.headers().hasHeaderWithName("Report-To"));

//for body, use jsonpath

       JsonPath jsonPath=response.jsonPath();

       assertEquals("United States",jsonPath.getString("country"));

       List<String> expectedZips=new ArrayList<>(Arrays.asList("02178","02478"));
       List<String> actualZips = jsonPath.getList("places.\'post code\'");

       assertEquals(expectedZips,actualZips);

       assertEquals("[42.4464,42.4128]",jsonPath.getString("latitude"));


       String ecpectedLatitude="[42.4464]";
       String actualLatitude=jsonPath.getString("places.findAll {it.\'post code\'==\"02178\"}.latitude");


   }









}
