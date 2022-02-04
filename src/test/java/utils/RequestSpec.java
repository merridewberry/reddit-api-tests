package utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static org.apache.http.HttpHeaders.USER_AGENT;
import static utils.PropertiesManager.getProperty;

public class RequestSpec {

    public static RequestSpecification requestSpec(boolean auth) {
        RequestSpecification spec = RestAssured.given()
                .filter(new AllureRestAssured())
                .header("User-Agent", USER_AGENT)
                .header("accept", "application/json");

        if (auth) {
            spec
                    .header("authorization", "Bearer " + getProperty("oauth-token"));
        }

        return spec;
    }

    public static RequestSpecification authSpec() {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .header("User-Agent", USER_AGENT)
                .header("accept", "application/json")
                .contentType(ContentType.URLENC.withCharset("UTF-8"));
    }
}
