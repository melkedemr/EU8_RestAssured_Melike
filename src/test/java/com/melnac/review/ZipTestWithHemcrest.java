package com.melnac.review;

import com.melnac.utilities.ZipBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ZipTestWithHemcrest extends ZipBase {
    @Test
    public void test() {

        given().log().all().accept(ContentType.JSON).
                pathParam("zip", "22031")
                .when().get("/{zip}")

                .then().assertThat().statusCode(200)
                .assertThat().contentType("application/json")
                .header("Server", equalTo("cloudflare"))
                .header("Report-To", notNullValue()).
                body("country", equalTo("United States"),
                        "places[0].state", equalTo("Virginia"),
                        "'post code'", equalTo("22031"),
                        "'country abbreviation'", is("US"),
                        "places[0].'place name'", is("Fairfax"),
                        "places[0].latitude", is("38.8604")).log().all();


    }
}
