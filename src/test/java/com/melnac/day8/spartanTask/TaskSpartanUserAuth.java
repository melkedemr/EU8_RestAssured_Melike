package com.melnac.day8.spartanTask;

import com.melnac.utilities.SpartanAuthTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

//User should be able to only READ data not update,delete,create (POST,PUT,PATCH,DELETE)

    public class TaskSpartanUserAuth extends SpartanAuthTestBase {

        @Test
        public void userCreateSpartanTest() {

            String request = "{\n" +
                    "  \"gender\": \"Male\",\n" +
                    "  \"name\": \"Monte Kristo\",\n" +
                    "  \"phone\": 4569871236\n" +
                    "}";

            Response response = given().header("Content-Type", "application/json")
                    .auth().basic("user", "user")
                    .body(request)
                    .post("/api/spartans")
                    .then().statusCode(403)
                    .extract().response();

            response.prettyPrint();
        }

        @Test
        public void userGetSpartanWithIdTest() {

            Response response = given().auth().basic("user", "user")
                    .pathParam("id", "104")
                    .get("/api/spartans/{id}")
                    .then().statusCode(200)
                    .extract().response();

            response.prettyPrint();
        }

        @Test
        public void userPutSpartanTest() {

            String request = "{\n" +
                    "  \"gender\": \"Male\",\n" +
                    "  \"name\": \"Monte Kristo\",\n" +
                    "  \"phone\": 4569871236\n" +
                    "}";

            Response response = given().header("Content-Type", "application/json")
                    .auth().basic("user", "user")
                    .body(request).pathParam("id", "48")
                    .put("/api/spartans/{id}")
                    .then().statusCode(403)
                    .extract().response();

            response.prettyPrint();
        }

        @Test
        public void userPatchSpartanTest() {

            String request = "{\n" +
                    "  \"name\": \"Monte Kristo\"\n" +
                    "}";

            Response response = given().header("Content-Type", "application/json")
                    .auth().basic("user", "user")
                    .body(request).pathParam("id", "70")
                    .patch("/api/spartans/{id}")
                    .then().statusCode(403)
                    .extract().response();

            response.prettyPrint();
        }

        @Test
        public void userDeleteSpartanWithIdTest() {

            Response response = given().auth().basic("user", "user")
                    .pathParam("id", "4")
                    .delete("/api/spartans/{id}")
                    .then().statusCode(403)
                    .extract().response();

            response.prettyPrint();
        }
}
