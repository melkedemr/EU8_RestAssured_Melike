package com.melnac.review;

import com.melnac.utilities.ZipBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZipCollectionTest extends ZipBase {
    @Test
    public void test() {

        Response response = given().log().all().accept(ContentType.JSON).
                pathParam("zip", "22031")
                .when().get("/{zip}");

        response.prettyPrint();

        Map <String, Object>postcode = response.body().as(Map.class);
        System.out.println("postcode = " + postcode);

        assertEquals("United States",postcode.get("country"));
        List<Map<String, Object>> places= (List<Map<String, Object>>) postcode.get("places");
        assertEquals("Virginia",places.get(0).get("state"));
        assertEquals("Fairfax", places.get(0).get("place name"));

    }}
