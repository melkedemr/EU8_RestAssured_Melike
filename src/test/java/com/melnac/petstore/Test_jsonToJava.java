package com.melnac.petstore;

import com.melnac.utilities.PetStoreBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class Test_jsonToJava extends PetStoreBase {

    /*
     Given accept type is json
     And path param id is 2
     When user sends a get request to "/store/order/{orderId}"
     Then status code is 200
     And content-type is "application/json"
     And header date is not null
     And response payload values match the following:

    "id": 2,
    "petId": 0,
    "quantity": 0,
    "shipDate": "2022-08-12T10:22:16.254+0000",
    "status": "placed",
    "complete": true

     */
    @DisplayName("Get request to /store/order/{orderId} for individual order")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("orderId", 2)
                .get("/store/order/{orderId}")
                .then().statusCode(200)
                .contentType("application/json")
                .header("Date", notNullValue())
                .extract().response();

        Map<String, Object> order2 = response.as(Map.class);
        System.out.println("order2 = " + order2);

        assertThat(order2.get("id"), is(2));
        assertThat(order2.get("petId"), equalTo(0));
        assertThat(order2.get("quantity"), is(0));
        assertThat(order2.get("shipDate"), is("2022-08-12T10:22:16.254+0000"));
        assertThat(order2.get("status"), equalTo("placed"));
        assertThat(order2.get("complete"), equalTo(true));


    }

    @DisplayName("Get request to /store/inventory to display inventory")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .get("/store/inventory")
                .then().statusCode(200)
                .contentType("application/json")
                .extract().response();

        Map<String, Object> inventory = response.as(Map.class);

        assertThat(inventory.get("sold"), is(15));
        assertThat(inventory.get("pending"), is(51));
        assertThat(inventory.get("for sale"), is(1));
        assertThat(inventory.get("adopted"), is(7));


    }

}
