package com.melnac.petstore;

import com.melnac.utilities.PetStoreBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Test_post extends PetStoreBase {

     /*
   Given accept type and Content type is JSON
   And request json body is:
   {

  "username": "string",
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "password": "string",
  "phone": "string",
  "userStatus": 0
  }
   When user sends POST request to '/user'
   Then status code 201
   And content type should be application/json
   And json payload/response/body should contain:
   code is 200

*/

    @DisplayName("Post request to /user ")
    @Test
    public void test1() {
        Map<String,Object> user= new HashMap<>();
        user.put("username","queen");
        user.put("lastName","Long");
        user.put("email","marry@gmail.com");
        user.put("phone","123456789456");
        user.put("password","MARRY");
        user.put("name","Marry");
       // user.put("id","2");
        user.put("status",0);

        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(user).log().all()
                .post("/user")
                .then().contentType(ContentType.JSON)
                .statusCode(200)
                .body("code",is(200))
                .body("message",is("9222968140497194086"));






    }


}
