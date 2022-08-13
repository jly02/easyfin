package com.easyfin.controllers;

import com.easyfin.helpers.Scenes;
import javafx.event.ActionEvent;

import java.io.IOException;

public class AddStockController {
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
