package com.melnac.day8.spartanTask;

import com.melnac.utilities.SpartanAuthTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

//Editor should be able to take all CRUD other than DELETE

    public class TaskSpartanEditorAuth extends SpartanAuthTestBase {

        @Test
        public void editorCreateSpartanTest(){

            String request = "{\n" +
                    "  \"gender\": \"Male\",\n" +
                    "  \"name\": \"Monte Kristo\",\n" +
                    "  \"phone\": 4569871236\n" +
                    "}";

            Response response = given().header("Content-Type", "application/json")
                    .auth().basic("editor", "editor")
                    .body(request)
                    .post("/api/spartans")
                    .then().statusCode(201)
                    .extract().response();

            response.prettyPrint();
        }

        @Test
        public void editorGetSpartanWithIdTest(){

            Response response = given().auth().basic("editor", "editor")
                    .pathParam("id", "104")
                    .get("/api/spartans/{id}")
                    .then().statusCode(200)
                    .extract().response();

            response.prettyPrint();
        }

        @Test
        public void editorPutSpartanTest(){

            String request = "{\n" +
                    "  \"gender\": \"Male\",\n" +
                    "  \"name\": \"Monte Kristo\",\n" +
                    "  \"phone\": 4569871236\n" +
                    "}";

            Response response = given().header("Content-Type", "application/json")
                    .auth().basic("editor", "editor")
                    .body(request).pathParam("id", "48")
                    .put("/api/spartans/{id}")
                    .then().statusCode(204)
                    .extract().response();

            response.prettyPrint();
        }

        @Test
        public void editorPatchSpartanTest(){

            String request = "{\n" +
                    "  \"name\": \"Monte Kristo\"\n" +
                    "}";

            Response response = given().header("Content-Type", "application/json")
                    .auth().basic("editor", "editor")
                    .body(request).pathParam("id", "70")
                    .patch("/api/spartans/{id}")
                    .then().statusCode(204)
                    .extract().response();

            response.prettyPrint();
        }

        @Test
        public void editorDeleteSpartanWithIdTest(){

            Response response = given().auth().basic("editor", "editor")
                    .pathParam("id", "4")
                    .delete("/api/spartans/{id}")
                    .then().statusCode(403)
                    .extract().response();

            response.prettyPrint();
        }
    }

