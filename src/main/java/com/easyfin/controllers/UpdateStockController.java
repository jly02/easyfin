package com.easyfin.controllers;

import com.easyfin.helpers.AccountAPIWrapper;
import com.easyfin.helpers.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Handles user adding stocks.
 */
public class UpdateStockController {
    @FXML private TextField symbolAddField;
    @FXML private TextField amountField;
    @FXML private TextField symbolRemoveField;
    @FXML private Label infoLabel;

    /**
     * Adds a stock to the account.
     */
    public void addStock() {
        // Symbol cannot be blank
        if(symbolAddField.getText().isBlank()) {
            infoLabel.setText("Symbol cannot be blank!");
            infoLabel.setTextFill(Color.RED);
            return;
        }

        String symbol = symbolAddField.getText();
        int amount;
        try {
            amount = Integer.parseInt(amountField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            amount = 1;
        }

        int status;
        try {
            status = AccountAPIWrapper.addStock(symbol, amount);
        } catch(URISyntaxException | IOException | InterruptedException e) {
            status = -1;
        }

        switch(status) {
            case 200:
                infoLabel.setText("Successfully added stock!");
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
     * Removes a stock from the account.
     */
    public void removeStock() {
        // Symbol cannot be blank
        if(symbolRemoveField.getText().isBlank()) {
            infoLabel.setText("Symbol cannot be blank!");
            infoLabel.setTextFill(Color.RED);
            return;
        }

        String symbol = symbolRemoveField.getText();
        int status;
        try {
            status = AccountAPIWrapper.removeStock(symbol);
        } catch(URISyntaxException | IOException | InterruptedException e) {
            status = -1;
        }

        switch(status) {
            case 200:
                infoLabel.setText("Successfully remove stock!");
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
     * Switch to the account screen.
     */
    public void toAccountScreen(ActionEvent event) throws IOException {
        Scenes.toAccountScreen(event);
    }

    /**
     * Switch to stock adding screen.
     */
    public void toMainScreen(ActionEvent event) throws IOException {
        Scenes.toMainScreen(event);
    }
}
