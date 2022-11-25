package com.melnac.day10;

import com.melnac.pojo.Spartan;
import com.melnac.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.*;

public class JsonSchemaValidationTest extends SpartanAuthTestBase {

    @DisplayName("GET request to verify one spartan against to schema")
    @Test
    public void schemaValidation() {

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 10)
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json"))
                .log().all();

    }

    @DisplayName("GET request to all spartans and verify schema")
    @Test
    public void allSpartanSchemaTest() {

        given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                //what if you have your .json file not under resources following way -->
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/melnac/day10/allSpartansSchema.json")));

    }

    //homework
    //put your post json schema under day10
    //post one spartan using dynamic input(name,gender,phone)
    //verify your post response matching with json schema

    @DisplayName("POST request to one spartan and verify schema")
    @Test
    public void potsASpartanSchemaTest() {
        Spartan spartan = new Spartan();
        spartan.setName("Jack");
        spartan.setGender("Male");
        spartan.setPhone(5414125158L);

       given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
               .auth().basic("admin","admin")
                .body(spartan)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("spartanPostJsonSchema.json")).log().all();


    }


}
