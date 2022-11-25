package com.melnac.day8.spartanTask;

import com.melnac.utilities.SpartanAuthTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

// Admin should be able take all CRUD

public class TaskSpartanAdminAuth extends SpartanAuthTestBase {

    @Test
    public void adminCreateSpartanTest(){

        String request = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Monte Kristo\",\n" +
                "  \"phone\": 4569871236\n" +
                "}";

        Response response = given().header("Content-Type", "application/json")
                .auth().basic("admin", "admin")
                .body(request)
                .post("/api/spartans")
                .then().statusCode(201)
                .extract().response();

        response.prettyPrint();
    }

    @Test
    public void adminGetSpartanWithIdTest(){

        Response response = given().auth().basic("admin", "admin")
                .pathParam("id", "104")
                .get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response();

        response.prettyPrint();
    }

    @Test
    public void adminPutSpartanTest(){

        String request = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Monte Kristo\",\n" +
                "  \"phone\": 4569871236\n" +
                "}";

        Response response = given().header("Content-Type", "application/json")
                .auth().basic("admin", "admin")
                .body(request).pathParam("id", "53")
                .put("/api/spartans/{id}")
                .then().statusCode(204)
                .extract().response();

        response.prettyPrint();
    }

    @Test
    public void adminPatchSpartanTest(){

        String request = "{\n" +
                "  \"name\": \"Monte Kristo\"\n" +
                "}";

        Response response = given().header("Content-Type", "application/json")
                .auth().basic("admin", "admin")
                .body(request).pathParam("id", "70")
                .patch("/api/spartans/{id}")
                .then().statusCode(204)
                .extract().response();

        response.prettyPrint();
    }

    @Test
    public void adminDeleteSpartanWithIdTest(){

        Response response = given().auth().basic("admin", "admin")
                .pathParam("id", "3")
                .delete("/api/spartans/{id}")
                .then().statusCode(204)
                .extract().response();

        response.prettyPrint();
    }
}
