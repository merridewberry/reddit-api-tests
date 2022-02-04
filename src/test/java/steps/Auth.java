package steps;

import io.cucumber.java.en.Given;

public class Auth {

    @Given("I have an authorization token")
    public static void setToken() {
        utils.SetToken.setToken();
    }
}
