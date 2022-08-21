package com.easyfin.helpers;

import com.easyfin.constructs.Credentials;
import com.easyfin.constructs.GetStocksResponse;
import com.google.gson.Gson;
import javafx.util.Pair;

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
     * @return the response to the POST request
     */
    public static HttpResponse<String> validate()
            throws IOException, InterruptedException, URISyntaxException {

        Pair<String, String> cred = Credentials.getCredentials();
        String username = cred.getKey();
        String apikey = cred.getValue();

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
     * @return a list of the names of stocks that a user has on their account
     */
    public static List<GetStocksResponse.Stock> getStocks()
            throws URISyntaxException, IOException, InterruptedException {

        Pair<String, String> cred = Credentials.getCredentials();
        String username = cred.getKey();
        String apikey = cred.getValue();

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

        // Converting to Stock class format.
        Gson gson = new Gson();
        GetStocksResponse response = gson.fromJson(getResponse.body(), GetStocksResponse.class);

        return response.getResponse();
    }

    /**
     * Sends a request for the given stock and its amount to be added to an account.
     *
     * @param symbol the stock symbol
     * @param amount the amount desired
     * @return the status code for this request
     */
    public static int addStock(String symbol, int amount) throws URISyntaxException {
        Pair<String, String> cred = Credentials.getCredentials();
        String username = cred.getKey();
        String apikey = cred.getValue();

        Gson gson = new Gson();

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://easyfin-api.herokuapp.com/add-stock"))
                .header("Content-Type", "application/json")
                .header("Authorization", apikey)
                .POST(HttpRequest.BodyPublishers.ofString()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
    }
}
