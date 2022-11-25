package com.melnac.day10;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class govXmlTest {



    @Test
    public void test1(){
        //send a get request to
        //https://data.ct.gov/api/views/qm34-pq7e/rows.xml
        //get all the years
        //get all unknowns
        //get 2005 other
        //get 2007 _address

        Response response = given().get("https://data.ct.gov/api/views/qm34-pq7e/rows.xml")
                .then().statusCode(200).extract().response();

        XmlPath xmlPath = response.xmlPath();

        List<Integer> years = xmlPath.getList("response.row.row.year");
        System.out.println("years = " + years);

        List<Object> unknowns = xmlPath.getList("response.row.row.unknown");

        int other2005 = xmlPath.getInt("response.row.row[2].other");

        String address2007 = xmlPath.getString("response.row.row[4].@_address");


    }
}
