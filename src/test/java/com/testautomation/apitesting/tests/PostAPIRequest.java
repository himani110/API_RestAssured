package com.testautomation.apitesting.tests;

import com.testautomation.apitesting.tests.util.BaseTest;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class PostAPIRequest extends BaseTest {

    @Test
    public void createBooking(){
        //prepare request body
        String postBody ="{\n" +
                "    \"firstname\": \"api testing\",\n" +
                "    \"additionalneeds\": \"breakfast\",\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2023-03-25\",\n" +
                "        \"checkout\": \"2023-03-30\"\n" +
                "    },\n" +
                "    \"totalprice\": 1000,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"lastname\": \"tutorial\"\n" +
                "}";
        Response response =
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .body(postBody)
                        .baseUri("https://restful-booker.herokuapp.com/booking")
                        //.log().all()
                        .when()
                        .post()
                        .then()
                        .assertThat()
                        //.log().body()
                        .statusCode(200)
                        .body("booking.firstname", Matchers.equalTo("api testing"))
                        .body("booking.totalprice", Matchers.equalTo(1000))
                        .body("booking.bookingdates.checkin", Matchers.equalTo("2023-03-25"))
                        .extract()
                        .response();

        int bookingId = response.path("bookingid");

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("bookingID", bookingId)
                .baseUri("https://restful-booker.herokuapp.com/booking")
                .when()
                .get("{bookingID}")
                .then()
                .assertThat()
                .statusCode(200)
                .body("firstname", Matchers.equalTo("api testing"))
                .body("lastname", Matchers.equalTo("tutorial"));
    }

}