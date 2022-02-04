package utils;

import io.restassured.response.ValidatableResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.lang.invoke.MethodHandles;

import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static utils.PropertiesManager.getProperty;

public class ResponseFormatCheck {

    private static final Logger log = (Logger) LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private static final Long responseTime = Long.valueOf(getProperty("responseTime"));

    public static ValidatableResponse assertSuccess(ValidatableResponse response) {
        ValidatableResponse assertedResponse = response
                .time(lessThanOrEqualTo(responseTime))
                .statusCode(200);

        log.debug("Response time: " + assertedResponse.extract().time() + "ms");

        return assertedResponse;
    }

    public static void assertDeletion(ValidatableResponse response) {
        ValidatableResponse assertedResponse = response
                .time(lessThanOrEqualTo(responseTime))
                .statusCode(204);

        log.debug("Response time: " + assertedResponse.extract().time() + "ms");
    }

    public static ValidatableResponse assertError(ValidatableResponse response, int statusCode) {
        ValidatableResponse assertedResponse = response
                .time(lessThanOrEqualTo(responseTime))
                .statusCode(statusCode);

        log.debug("Response time: " + assertedResponse.extract().time() + "ms");

        return response;
    }

}
