package com.melnac.day5;

import com.melnac.utilities.DBUtils;
import com.melnac.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class SpartanAPIvsDB extends SpartanTestBase {

    @DisplayName("GET one spartan from api and database")
    @Test
    public void testDB1() {
        //get id,name,gender phone  from database
        //get same information from api
        //compare
        //1. get id,name,gender phone  from database

        String query = "select name,gender,SPARTAN_ID,phone from spartans where SPARTAN_ID=15";
        //save data inside map

        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);

        //get info from api

        Map <String,Object>apiMap = given().accept(ContentType.JSON)
                .pathParam("id", "15")
                .when()
                .get("api/spartans/{id}")
                .then().statusCode(200)
                .and().contentType("application/json")
                .extract().response().as(Map.class);


        System.out.println(apiMap);

        assertThat(apiMap.get("id").toString(),is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(apiMap.get("name"),is(dbMap.get("NAME")));
        assertThat(apiMap.get("gender"),is(dbMap.get("GENDER")));
        assertThat(apiMap.get("phone").toString(),is(dbMap.get("PHONE").toString()));

    }


}
