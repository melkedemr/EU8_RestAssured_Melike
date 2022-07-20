package com.melnac.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.config;

public abstract class HRTestBase {
    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://34.230.72.55:1000/ords/hr";

        //get ip address from configurations
        String dbUrl =ConfigurationReader.getProperty("dbUrl");
        String dbUsername = ConfigurationReader.getProperty("dbUsername");
        String dbPassword = ConfigurationReader.getProperty("dbPassword");

       DBUtils.createConnection(dbUrl,dbUsername,dbPassword);
    }

    @AfterAll
    public static void teardown(){

        DBUtils.destroy();
    }
}
