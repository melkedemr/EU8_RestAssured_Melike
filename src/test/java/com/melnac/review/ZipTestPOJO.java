package com.melnac.review;

import com.melnac.utilities.ZipBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ZipTestPOJO extends ZipBase {

    @Test
    public void test() {
        Response response = given().log().all().accept(ContentType.JSON).
                pathParam("zip", "22031")
                .when().get("/{zip}");
        response.prettyPrint();


        Postcode zip22031 = response.body().as(Postcode.class);
        System.out.println("zip22031.getCountry() = " + zip22031.getCountry());

        assertEquals("United States",zip22031.getCountry());
        assertEquals("Virginia", zip22031.getPlaces().get(0).getState());
        assertEquals("VA",zip22031.getPlaces().get(0).getStateAbbreviation());


    }
}
