package steps;

import io.cucumber.java.en.Then;
import utils.Context;
import utils.ResponseFormatCheck;

public class GenericSteps {

    @Then("^I receive a response with status code (\\d+)$")
    public static void checkResponseStatusCode(int statusCode) {
        ResponseFormatCheck.assertError(Context.responseBuffer, statusCode);
    }
}
