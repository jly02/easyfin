package com.easyfin.controllers;

import com.easyfin.helpers.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Handles user adding stocks.
 */
public class UpdateStockController {
    @FXML private TextField symbolAddField;
    @FXML private TextField amountField;
    @FXML private TextField symbolRemoveField;

    /**
     * Adds a stock to the account.
     */
    public void addStock() {
        String symbol = symbolAddField.getText();
        int amount;
        try {
            amount = Integer.parseInt(amountField.getText());
        } catch(NumberFormatException e) {
            e.printStackTrace();
            amount = 1;
        }

    }

    /**
     * Removes a stock from the account.
     */
    public void removeStock() {

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
