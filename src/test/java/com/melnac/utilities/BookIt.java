package com.melnac.utilities;

import static io.restassured.RestAssured.given;

public abstract class BookIt {
    //create BookItUtil then create a method, that accepts email and password return token Bearer +yourToken as a String
    public static String token(String email, String password){

        return "Bearer " + given().queryParam("email", email)
                .queryParam("password", password)
                .get("https://cybertek-reservation-api-qa3.herokuapp.com/sign")
                .jsonPath().getString("accessToken");
    }
}


