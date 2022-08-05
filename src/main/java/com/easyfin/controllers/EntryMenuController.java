package com.easyfin.controllers;

import com.easyfin.constructs.ColoredText;
import com.easyfin.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

/**
 * Handles actions on the screen that shows your personal portfolio.
 */
public class EntryMenuController implements Initializable {
    // Color used for text in left list view: 0xbfbfbf
    private static final Color TEXT_GREY = new Color(
            (double) 0xbf / 0x100,
            (double) 0xbf / 0x100,
            (double) 0xbf/ 0x100,
            1
    );

    private static final String[] MONTHS = {
            "JAN", "FEB", "MAR",
            "APR", "MAY", "JUN",
            "JUL", "AUG", "SEP",
            "NOV", "OCT", "DEC"
    };

    @FXML private ListView<ColoredText> stocks;
    @FXML private Label graphTitle;
    @FXML private LineChart<String, Integer> stockGraph;

    List<ColoredText> stockList = new ArrayList<>();

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IntStream
            .range(0, 20)
            .forEach(i -> stockList.add(new ColoredText("WEQE", TEXT_GREY)));

        stocks.getItems().addAll(stockList);
        graphTitle.setText(stockList.get(0).getText());

        // Set all colors
        stocks.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(ColoredText item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setText(null);
                    setTextFill(null);
                } else {
                    setText(item.getText());
                    setTextFill(item.getColor());
                }
            }
        });

        // Add items to graph
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Stock value over time");
        IntStream
                .range(0, 12)
                .forEach(i -> series.getData().add(new XYChart.Data<>(MONTHS[i], (i + 1) * 4)));

        stockGraph.getData().addAll(series);

        // Set graph label text when a different item is picked
        stocks.getSelectionModel().selectedItemProperty().addListener((observableValue, coloredText, t1) -> {
            String newTitle = stocks.getSelectionModel().getSelectedItem().getText();
            graphTitle.setText(newTitle);
        });
    }

    /**
     * Switch to the account screen.
     */
    public void toAccountScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("account-screen.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}