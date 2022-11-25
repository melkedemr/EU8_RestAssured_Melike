package com.melnac.petstore;

import com.melnac.utilities.PetStoreBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test_id extends PetStoreBase {

       /*
     Given accept type is json
     And path param id is 5
     When user sends a get request to "/pet/:petId"
     Then status code is 200
     And content-type is "application/json"
     And header includes  "keep-alive"
     And header includes "Transfer-Encoding"
     And response payload values match the following:
        status is pending,
        category id is 5

   */

    @DisplayName("Get request to /pet/{petId} for id")
    @Test
    public void test(){

        given().accept(ContentType.JSON)
                .pathParam("petId",5)
                .when().log().all().get("/pet/{petId}")
                .then().assertThat() .statusCode(200)
                .assertThat().contentType("application/json")
                .assertThat().header("Connection",is("keep-alive"))
                .assertThat().header("Transfer-Encoding",is("chunked"))
                .assertThat().body("status",is("pending"))
                .assertThat().body("category.id",is(5));




 }


}
