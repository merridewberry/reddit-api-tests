package endpoints.api.v1.me;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utils.Basic;
import utils.Endpoints;
import utils.RequestSpec;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static utils.PropertiesManager.getProperty;

public class Karma extends Basic {
    private static final String karma = Endpoints.karma;

    public static ValidatableResponse get(boolean auth) {
        RequestSpecification request = RequestSpec.requestSpec(auth);

        return request
                .get(getProperty("baseUri") + karma)
                .then();
    }

    public static ValidatableResponse get_valid(ValidatableResponse response) {
        ValidatableResponse assertedResponse = response
                .time(lessThanOrEqualTo(responseTime))
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/karma.json"));

        log.debug("Response time: " + assertedResponse.extract().time() + "ms");

        return assertedResponse;
    }

}
