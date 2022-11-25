package com.melnac.utilities;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.basePath;
public class ZipBase {


    @BeforeAll
    public static void init(){

        baseURI= "http://api.zippopotam.us";
        basePath= "/us";
    }


}
