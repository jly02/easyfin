package com.easyfin.controllers;

import com.easyfin.App;
import com.easyfin.constructs.Credentials;
import com.easyfin.constructs.ResourceManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Handles all actions taking place on the account screen,
 * IE logging in and testing connections.
 */
public class AccountScreenController implements Initializable {
    @FXML private TextField usernameField;
    @FXML private PasswordField apiField;
    @FXML private Label infoLabel;

    /**
     * Switch to the main stocks screen.
     */
    public void toMainScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("entry-menu.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Tests the inputted credentials against the database.
     */
    public void testValidate() {
        infoLabel.setText("Successfully Connected!");
        infoLabel.setTextFill(Color.GREEN);
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
}
