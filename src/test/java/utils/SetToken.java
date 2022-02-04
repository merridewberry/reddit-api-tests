package utils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static utils.PropertiesManager.getProperty;
import static utils.PropertiesManager.setProperty;

public class SetToken {
    static final String accessToken = Endpoints.accessToken;

    public static void setToken() {
        String token = given()
                .spec(RequestSpec.authSpec())
                .auth().preemptive().basic(getProperty("client-id"), getProperty("client-secret"))
                .formParam("grant_type", "password")
                .formParam("username", getProperty("username"))
                .formParam("password", getProperty("password"))
                .post(getProperty("authUri") + accessToken)
                .then()
                .statusCode(200)
                .body("$", hasKey("access_token"))
                .extract().path("access_token");

        setProperty("oauth-token", token);
    }
}
