package com.easyfin.constructs;

import lombok.Getter;

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
}
