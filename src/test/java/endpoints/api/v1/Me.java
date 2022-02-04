package endpoints.api.v1;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utils.Basic;
import utils.Endpoints;
import utils.RequestSpec;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static utils.PropertiesManager.getProperty;

public class Me extends Basic {
    private static final String me = Endpoints.me;

    public static ValidatableResponse get(boolean auth) {
        RequestSpecification request = RequestSpec.requestSpec(auth);

        return request
                .get(getProperty("baseUri") + me)
                .then();
    }

    public static ValidatableResponse get_valid(ValidatableResponse response) {
        ValidatableResponse assertedResponse = response
                .time(lessThanOrEqualTo(responseTime))
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/me.json"))
                .body("subreddit.display_name", equalTo("u_" + getProperty("username")));

        log.debug("Response time: " + assertedResponse.extract().time() + "ms");

        return assertedResponse;
    }
}
