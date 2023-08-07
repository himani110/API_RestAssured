package com.testautomation.apitesting.tests.util;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.Map;
public class HelperMethods {

    public static JsonPath fetchGetAllBookingsResponse() {
        Response res =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .baseUri("https://restful-booker.herokuapp.com/booking")
                        .when()
                        .get()
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .statusLine("HTTP/1.1 200 OK")
                        .extract().response();
        Assert.assertTrue(res.getBody().asString().contains("bookingid"));
        return (JsonPath) (JsonPath) res;
    }
}