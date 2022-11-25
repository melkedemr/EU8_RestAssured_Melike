package com.melnac.day8;

import com.melnac.utilities.BookIt;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BookItTest {
    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com";

    }

    //create BookItUtil then create a method, that accepts email and password return token Bearer +yourToken as a String

   // String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMzkiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0._vM1-eRoS7SsHu6T-QPdJoEdA8LSwnxUvvTTbhV-8ms";
    String accessToken= BookIt.token("sbirdbj@fc2.com","asenorval");
    @DisplayName("GET all campuses")
    @Test
    public void testAuth1(){
        
        given().
                header("Authorization",accessToken)
                .and().accept(ContentType.JSON)
                .when()
                .get("/api/campuses")
                .then()
                .statusCode(200)
                .log().all();

    }

}
