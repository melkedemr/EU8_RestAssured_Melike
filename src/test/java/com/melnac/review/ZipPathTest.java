package com.melnac.review;

import com.melnac.utilities.ZipBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ZipPathTest extends ZipBase {
    @Test
    public void pathTest() {

        Response response = given().accept(ContentType.JSON)
                .pathParam("zip", 22031)
                .when()
                .get("/{zip}");

        response.prettyPrint();

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.header("Server").equalsIgnoreCase("cloudflare"));

        assertTrue(response.headers().hasHeaderWithName("Report-To"));

        // For body, we use path method

        assertEquals("22031",response.path("\'post code\'"));
        assertEquals("United States",response.path("country"));
        assertEquals("US",response.path("\'country abbreviation\'"));
        assertEquals("Fairfax",response.path("places[0].\'place name\'"));
        assertEquals("Virginia",response.path("places[0].state"));
        assertEquals("38.8604",response.path("places[0].latitude"));

        JsonPath jsonPath=response.jsonPath();
        assertEquals("United States",jsonPath.getString("country"));
    }
}
