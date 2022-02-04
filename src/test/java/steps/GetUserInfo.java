package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.Context;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.contains;
import static utils.PropertiesManager.setProperty;

public class GetUserInfo {

    @When("I request user info")
    public static void getMe() {
        Context.responseBuffer = endpoints.api.v1.Me.get(true);
    }

    @Then("I get valid user info")
    public static void getMe_valid() {
        endpoints.api.v1.Me.get_valid(Context.responseBuffer);
    }

    @When("I request user info without an access token")
    public static void getMe_noToken() {
        Context.responseBuffer = endpoints.api.v1.Me.get(false);
    }

    @When("^I request user info with access token equal to (.+)$")
    public static void getMe_wrongToken(String token) {
        setProperty("oauth-token", token);
        Context.responseBuffer = endpoints.api.v1.Me.get(true);
    }

    @When("I request user karma info")
    public static void getKarma() {
        Context.responseBuffer = endpoints.api.v1.me.Karma.get(true);
    }

    @Then("I get valid user karma info")
    public static void getKarma_valid() {
        endpoints.api.v1.me.Karma.get_valid(Context.responseBuffer);
    }

    @Then("^I get valid user karma info, where on (.+) subreddit comment karma equals (\\d+) and link karma equals (\\d+)$")
    public static void getKarmaSpecificSub_valid(String sub, int comment, int link) {
        endpoints.api.v1.me.Karma.get_valid(Context.responseBuffer)
                .body("data.sr", contains(sub))
                .body("data.comment_karma", contains(comment))
                .body("data.link_karma", contains(link));
    }

    @When("I request user karma info without an access token")
    public static void getKarma_noToken() {
        Context.responseBuffer = endpoints.api.v1.me.Karma.get(false);
    }

    @When("^I request user karma info with access token equal to (.+)$")
    public static void getKarma_wrongToken(String token) {
        setProperty("oauth-token", token);
        Context.responseBuffer = endpoints.api.v1.me.Karma.get(true);
    }

    @When("I request user preferences info")
    public static void getPrefs() {
        Context.responseBuffer = endpoints.api.v1.me.Prefs.get(true);
    }

    @Then("I get valid user preferences info")
    public static void getPrefs_valid() {
        endpoints.api.v1.me.Prefs.get_valid(Context.responseBuffer);
    }

    @When("I request user preferences info without an access token")
    public static void getPrefs_noToken() {
        Context.responseBuffer = endpoints.api.v1.me.Prefs.get(false);
    }

    @When("^I request user preferences info with access token equal to (.+)$")
    public static void getPrefs_wrongToken(String token) {
        setProperty("oauth-token", token);
        Context.responseBuffer = endpoints.api.v1.me.Prefs.get(true);
    }

    @When("I send a request to edit user preferences with no data")
    public static void patchPrefs_noData() {
        Map<String, String> body = new HashMap<>();
        Context.responseBuffer = endpoints.api.v1.me.Prefs.patch(true, body);
    }

    @When("I send a request to edit user preferences with the following data:")
    public static void patchPrefs_table(Map<String, String> body) {
        Context.body = body;
        Context.responseBuffer = endpoints.api.v1.me.Prefs.patch(true, body);
    }

    @When("^I send request to change (.+) setting in the user preferences to (.+)$")
    public static void patchPrefs(String key, String value) {
        Map<String, String> body = new HashMap<>();
        body.put(key, value);

        Context.body = body;
        Context.responseBuffer = endpoints.api.v1.me.Prefs.patch(true, body);
    }

    @Then("I get valid user preferences info with changed data")
    public static void checkPatchedPrefs() {
        endpoints.api.v1.me.Prefs.patch_valid(Context.responseBuffer, Context.body);
    }

    @When("I send request to patch user preferences info without an access token")
    public static void patchPrefs_noToken() {
        Map<String, String> body = new HashMap<>();
        Context.responseBuffer = endpoints.api.v1.me.Prefs.patch(false, body);
    }

    @When("^I send request to patch user preferences info with access token equal to (.+)$")
    public static void patchPrefs_wrongToken(String token) {
        setProperty("oauth-token", token);
        Map<String, String> body = new HashMap<>();
        Context.responseBuffer = endpoints.api.v1.me.Prefs.patch(true, body);
    }

}
