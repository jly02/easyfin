package com.easyfin.controllers;

import com.easyfin.constructs.Credentials;
import com.easyfin.helpers.AccountAPIWrapper;
import com.easyfin.helpers.ResourceManager;

import com.easyfin.helpers.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;
import java.net.URISyntaxException;

/**
 * Handles all actions taking place on the account screen,
 * IE logging in and testing connections.
 */
public class AccountScreenController implements Initializable {
    @FXML private TextField usernameField;
    @FXML private PasswordField apiField;
    @FXML private Label infoLabel;

    /**
     * Tests the inputted credentials against the database.
     */
    public void testValidate() throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> postResponse = AccountAPIWrapper.validate(usernameField.getText(), apiField.getText());

        switch(postResponse.statusCode()) {
            case 200:
                infoLabel.setText("Successfully Connected!");
                infoLabel.setTextFill(Color.GREEN);
                break;

            case 401:
                infoLabel.setText("Unable to authenticate credentials.");
                infoLabel.setTextFill(Color.RED);
                break;

            case 404:
                infoLabel.setText("Username not found.");
                infoLabel.setTextFill(Color.RED);
                break;

            default:
                infoLabel.setText("Unknown error occurred.");
                infoLabel.setTextFill(Color.RED);
        }
    }

    /**
     * Saves user's inputted credentials as local persistent data.
     */
    public void saveCredentials() throws IOException {
        // Neither field can be blank
        if(usernameField.getText().isBlank() || apiField.getText().isBlank()) {
            infoLabel.setText("Fields cannot be empty!");
            infoLabel.setTextFill(Color.RED);
            return;
        }

        // Write to validation.save file
        ResourceManager.save(
                new Credentials(usernameField.getText(), apiField.getText()),
                "src/main/resources/persistent/validation.save"
        );
    }

    /**
     * Opens the website for registering a username and getting an API key.
     */
    public void openLink() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://easyfin-api.herokuapp.com/getkey"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Credentials cred = (Credentials) ResourceManager.load("src/main/resources/persistent/validation.save");
            usernameField.setText(cred.getUsername());
            apiField.setText(cred.getApiKey());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Switch to the main stocks screen.
     */
    public void toMainScreen(ActionEvent event) throws IOException {
        Scenes.toMainScreen(event);
    }

    /**
     * Switch to stock adding screen.
     */
    public void toStockScreen(ActionEvent event) throws IOException {
        Scenes.toStockScreen(event);
    }
}
