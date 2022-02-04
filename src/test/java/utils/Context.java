package utils;

import io.restassured.response.ValidatableResponse;

import java.util.Map;

public class Context {

    public static ValidatableResponse responseBuffer;
    public static Map<String, String> body;

}
