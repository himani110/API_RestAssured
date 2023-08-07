package com.testautomation.apitesting.tests.util;

import org.testng.annotations.BeforeMethod;
import io.restassured.RestAssured;


public class BaseTest {
    @BeforeMethod
    public void beforeMethod(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }
}
