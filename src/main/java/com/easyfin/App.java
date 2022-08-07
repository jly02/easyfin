package com.easyfin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("entry-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add(Objects.requireNonNull(
                App.class.getResource("entry-menu-style.css")).toExternalForm()
        );

        stage.getIcons().add(new Image(Objects.requireNonNull(
                App.class.getResourceAsStream("/img/icon.png"))
        ));

        stage.setResizable(false);
        stage.setTitle("Easy-Fin Stocks");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}