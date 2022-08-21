package com.easyfin.constructs;

import com.easyfin.helpers.ResourceManager;
import javafx.util.Pair;
import lombok.Getter;

import java.io.IOException;

/**
 * Serializable class for storing user credentials between
 * each run of the program and switching screens.
 */
@Getter
public class Credentials implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private final String username;
    private final String apiKey;

    public Credentials(String username, String apiKey) {
        this.username = username;
        this.apiKey = apiKey;
    }

    /**
     * Gets the currently stored credentials for a user.
     *
     * @return a 2-tuple consisting of the username and apikey that was previously stored
     */
    public static Pair<String, String> getCredentials() {
        Credentials cred = null;
        try {
            cred = (Credentials) ResourceManager.load("src/main/resources/persistent/validation.save");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        assert cred != null;
        return new Pair<>(cred.getUsername(), cred.getApiKey());
    }
}
