package com.poc.tests.api;

import com.utils.AllureReportUtil;
import com.utils.ConfigReader;
import io.restassured.http.ContentType;
import io.qameta.allure.restassured.AllureRestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CoinApiTest {

    private String coinsUrl;

    @BeforeSuite
    public void beforeSuite() {
        AllureReportUtil.generateEnvironmentProperties();
    }

    @BeforeClass
    public void setup() {
        String baseUrl = ConfigReader.getProperty("api.base_url");
        String coinsPath = ConfigReader.getProperty("api.coins_path");
        coinsUrl = baseUrl + coinsPath;
    }

    @Test(description = "Verify that the coins endpoint returns 200 OK and is in JSON format")
    public void testGetCoinsStatusCodeAndContentType() {
        given()
            .filter(new AllureRestAssured())
            .when()
                .get(coinsUrl)
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test(description = "Verify that the coins list contains Bitcoin (btc-bitcoin)")
    public void testCoinsListContainsBitcoin() {
        given()
            .filter(new AllureRestAssured())
            .when()
                .get(coinsUrl)
            .then()
                .body("id", hasItem("btc-bitcoin"))
                .body("name", hasItem("Bitcoin"))
                .body("symbol", hasItem("BTC"));
    }
}
