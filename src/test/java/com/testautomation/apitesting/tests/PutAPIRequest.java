package com.testautomation.apitesting.tests;

import java.io.File;
import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.testautomation.apitesting.tests.util.FileNameConstants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;

public class PutAPIRequest {

    @Test
    public void putAPIRequest() throws IOException {

        String postAPIRequestBody = "{\n" +
                "    \"firstname\": \"Postapi_PutTesting\",\n" +
                "    \"lastname\": \"tutorial\",\n" +
                "    \"totalprice\": 5000,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-01-01\",\n" +
                "        \"checkout\": \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"super bowls\"\n" +
                "}";
        //post api call
        Response response =
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .body(postAPIRequestBody)
                        .baseUri("https://restful-booker.herokuapp.com/booking")
                        .when()
                        .post()
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response();

        JSONArray jsonArray = JsonPath.read(response.body().asString(),"$.booking..firstname");
        String firstName = (String) jsonArray.get(0);

        Assert.assertEquals(firstName, "Postapi_PutTesting");

        int bookingId = JsonPath.read(response.body().asString(),"$.bookingid");

        //get api call
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .when()
                .get("/{bookingId}",bookingId)
                .then()
                .assertThat()
                .statusCode(200);

        //token generation
        String tokenAPIRequestBody ="{\n" +
                "    \"username\":\"admin\",\n" +
                "    \"password\":\"password123\"\n" +
                "\n" +
                "}";
        Response tokenAPIResponse =
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .body(tokenAPIRequestBody)
                        .baseUri("https://restful-booker.herokuapp.com/auth")
                        .when()
                        .post()
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response();

        String token = JsonPath.read(tokenAPIResponse.body().asString(),"$.token");

        //put api call
        String putAPIRequestBody= "{\n" +
                "    \"firstname\": \"Specflow\",\n" +
                "    \"lastname\": \"Selenium C#\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-01-01\",\n" +
                "        \"checkout\": \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"super bowls\"\n" +
                "}";
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(putAPIRequestBody)
                .header("Cookie", "token="+token)
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .when()
                .put("/{bookingId}",bookingId)
                .then()
                .assertThat()
                .statusCode(200)
                .body("firstname", Matchers.equalTo("Specflow"))
                .body("lastname", Matchers.equalTo("Selenium C#"));

    }

}