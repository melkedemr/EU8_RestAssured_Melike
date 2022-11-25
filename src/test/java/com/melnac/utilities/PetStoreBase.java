package com.melnac.utilities;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.baseURI;

public class PetStoreBase {

    @BeforeAll
    public static void init(){

        baseURI="https://petstore.swagger.io/v2";

    }


}
