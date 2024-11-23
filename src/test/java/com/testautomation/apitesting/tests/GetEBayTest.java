package com.testautomation.apitesting.tests;

import com.testautomation.apitesting.tests.util.HelperMethod1;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;
import java.util.ArrayList;
import static org.testng.AssertJUnit.assertEquals;

public class GetEBayTest {
    @Test()
    void ValidateTheConditions() {
        JsonPath jp = HelperMethod1.fetchGetResponse();

        //Verifying First Condition [3 BPIs i. USD ii. GBP iii. EUR]
        String[] requiredCurrencies = {"USD", "GBP", "EUR"};
        Map bpiMap = jp.getMap("bpi");
        for (String currency : requiredCurrencies) {
            Assert.assertTrue(bpiMap.containsKey(currency),
                    "Currency " + currency + " is missing!");
        }

        //Verifying Second Condition [b. The GBP ‘description’ equals ‘British Pound Sterling’.]
        String GBPDescription = jp.get("bpi.GBP.description");
        assertEquals("AssertionPassed", GBPDescription, "British Pound Sterling");
        System.out.println("All required currencies are present and GBP description is correct.");
    }
    }