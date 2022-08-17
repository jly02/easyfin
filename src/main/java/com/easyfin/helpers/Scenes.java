package com.easyfin.helpers;

import com.easyfin.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class holds only static methods that facilitate switching
 * between different scenes in the application.
 */
public class Scenes {
    /**
     * Switch to account screen.
     *
     * @param event for finding the source of the click
     */
    public static void toAccountScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("account-screen.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switch to main screen.
     *
     * @param event for finding the source of the click
     */
    public static void toMainScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("entry-menu.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switch to stock adding screen.
     *
     * @param event for finding the source of the click
     */
    public static void toStockScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("add-stock-view.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
