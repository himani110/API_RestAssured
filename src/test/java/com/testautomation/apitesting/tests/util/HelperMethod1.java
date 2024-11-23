package com.testautomation.apitesting.tests.util;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

public class HelperMethod1 {
    public static JsonPath fetchGetResponse(){
        Response res =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .baseUri("https://api.coindesk.com/v1/bpi")
                        .when()
                        .get("/currentprice.json")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .statusLine("HTTP/1.1 200 OK")
                        .extract().response();
        String json = res.asString();
        JsonPath jp = new JsonPath(json);
//        System.out.println("Pretty Get Response:" + jp.prettify());
        return jp;
    }
}
