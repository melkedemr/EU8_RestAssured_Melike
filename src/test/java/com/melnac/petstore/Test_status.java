package com.melnac.petstore;

import com.melnac.utilities.PetStoreBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_status extends PetStoreBase {


     /*
     Given accept type is json
     And querry param status available
     When user sends a get request to "/pet/findByStatus"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          status is available,
          name is "doggie",
          id is "9222968140497181135"

   */
     @DisplayName("GET request to /pet/findByStatus for available pets")
     @Test
    public void test1(){

         Response response = given().accept(ContentType.JSON)
                 .queryParam("status", "available")
                 .when().get("/pet/findByStatus");

         assertEquals(200,response.statusCode());
         assertEquals("application/json",response.contentType());
         assertEquals( 9222968140497181135l, (Long) response.path("id[0]"));
         assertEquals("available",response.path("status[0]"));
         assertEquals("doggie",response.path("name[0]"));


     }

     /*
          Given accept type is json
          And querry param status pending
          When user sends a get request to "/pet/findByStatus"
          Then status code is 200
          And content-type is "application/json"
          And response payload values match the following:
               id is 339,
               status is pending,
               category id is 0,
               tag name is "<tag>"

        */
     @DisplayName("GET request to /pet/findByStatus for available pets")
     @Test
     public void test2(){
          Response response = given().accept(ContentType.JSON)
                  .queryParam("status", "pending")
                  .when().log().all().get("/pet/findByStatus");


     assertEquals(200,response.statusCode());
     assertEquals("application/json",response.contentType());

         JsonPath jsonPath= response.jsonPath();

         assertEquals(339,jsonPath.getInt("id[0]"));
         assertEquals("pending",jsonPath.getString("status[0]"));
         assertEquals(0,jsonPath.getInt("category[0].id"));
         assertEquals(0,jsonPath.getInt("category.id[0]"));
         assertEquals("<tag>",jsonPath.get("tags[0].name[0]"));


}
}
