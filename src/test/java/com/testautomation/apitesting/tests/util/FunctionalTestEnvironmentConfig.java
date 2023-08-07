package com.testautomation.apitesting.tests.util;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.SSLConfig;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FunctionalTestEnvironmentConfig {
    public RequestSpecification buildRequestSpecification(String postTo) {
        return this.buildBasicRequestSpecification(postTo);
    }

    private RequestSpecification buildBasicRequestSpecification(String postTo) {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.config().sslConfig(
                new SSLConfig().allowAllHostnames());
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();

        Properties
                prop = new Properties();
        InputStream input = null;


        Map<String, String> headersMap = new HashMap<>();
        headersMap.put(prop.getProperty("x_request_id_name"), prop.getProperty("x_request_id_value"));
        headersMap.put(prop.getProperty("x_client_id_name"), prop.getProperty("x_client_id_value"));
        headersMap.put(prop.getProperty("x_client_version_name"), prop.getProperty("x_client_version_value"));
        headersMap.put(prop.getProperty("accept_name"), prop.getProperty("accept_value"));

        requestBuilder
                .addHeaders(headersMap)
                .setBaseUri(prop.getProperty("base_uri"));

        switch (postTo) {
            case "getAllBooking":
                requestBuilder.addHeaders(headersMap).setBasePath("restful-booker.herokuapp.com/booking");
                break;

        }

        return requestBuilder.build();
    }

}
