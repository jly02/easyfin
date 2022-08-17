package com.easyfin.helpers;

import com.easyfin.constructs.GetStocksResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

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

    /**
     * Sends a request to the EasyFin API to get all the stocks on an account.
     *
     * @param username the username of the account
     * @param apikey the apikey associated with the given username
     * @return a list of the names of stocks that a user has on their account
     */
    public static List<GetStocksResponse.Stock> getStocks(String username, String apikey)
            throws URISyntaxException, IOException, InterruptedException {

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://easyfin-api.herokuapp.com/get-stocks/" + username))
                .header("Content-Type", "application/json")
                .header("Authorization", apikey)
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        // Usually account not found or unable to authenticate.
        if(getResponse.statusCode() != 200) {
            return new ArrayList<>(){};
        }

        // Converting to ColoredText format.
        Gson gson = new Gson();
        GetStocksResponse response = gson.fromJson(getResponse.body(), GetStocksResponse.class);

        return response.getResponse();
    }
}
