package com.testautomation.apitesting.tests;

import com.testautomation.apitesting.tests.util.FunctionalTestEnvironmentConfig;
import com.testautomation.apitesting.tests.util.HelperMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
public class GetAPIRequest {
    @Test
     void getAllBookings()
    {
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
    }
}
