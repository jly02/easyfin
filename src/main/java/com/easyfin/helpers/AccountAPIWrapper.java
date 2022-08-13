package com.easyfin.helpers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AccountAPIWrapper {
    /**
     * Sends a request to the EasyFin API for test validation.
     *
     * @param username the username to be validated
     * @param apikey the apikey associated with the given username
     * @return the response to the POST request
     */
    public static HttpResponse<String> validate(String username, String apikey)
            throws IOException, InterruptedException, URISyntaxException {

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://easyfin-api.herokuapp.com/test-login"))
                .header("Content-Type", "application/json")
                .header("Authorization", apikey)
                .POST(HttpRequest.BodyPublishers.ofString(String.format("{ \"username\": \"%s\" }", username)))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        return httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
    }
}
