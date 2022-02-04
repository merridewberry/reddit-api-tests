package endpoints.api.v1.me;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utils.Basic;
import utils.Endpoints;
import utils.RequestSpec;

import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static utils.PropertiesManager.getProperty;

public class Prefs extends Basic {
    private static final String prefs = Endpoints.prefs;

    public static ValidatableResponse get(boolean auth) {
        RequestSpecification request = RequestSpec.requestSpec(auth);

        return request
                .get(getProperty("baseUri") + prefs)
                .then();
    }

    public static ValidatableResponse get_valid(ValidatableResponse response) {
        ValidatableResponse assertedResponse = response
                .time(lessThanOrEqualTo(responseTime))
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/prefs.json"));

        log.debug("Response time: " + assertedResponse.extract().time() + "ms");

        return assertedResponse;
    }

    public static ValidatableResponse patch(boolean auth, Map<String, String> body) {
        RequestSpecification request = RequestSpec.requestSpec(auth);

        return request
                .body(body)
                .patch(getProperty("baseUri") + prefs)
                .then();
    }

    public static ValidatableResponse patch_valid(ValidatableResponse response, Map<String, String> body) {
        ValidatableResponse assertedResponse = response
                .time(lessThanOrEqualTo(responseTime))
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/prefs.json"));

        for (Map.Entry<String, String> entry : body.entrySet()) {
            if (entry.getValue().matches("^-?\\d+$")) {
                response.body(entry.getKey(), equalTo(Integer.valueOf(entry.getValue())));
            } else if (entry.getValue().matches("true|false")) {
                response.body(entry.getKey(), equalTo(Boolean.valueOf(entry.getValue())));
            } else {
                response.body(entry.getKey(), equalTo(entry.getValue()));
            }
        }

        log.debug("Response time: " + assertedResponse.extract().time() + "ms");

        return assertedResponse;
    }

}
