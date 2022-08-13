package com.easyfin.controllers;

import com.easyfin.constructs.ColoredText;
import com.easyfin.App;

import com.easyfin.constructs.StockEntry;
import com.easyfin.helpers.TextFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

/**
 * Handles actions on the screen that shows your personal portfolio.
 */
public class EntryMenuController implements Initializable {
    @FXML private ListView<ColoredText> stocks;
    @FXML private Label graphTitle;
    @FXML private LineChart<String, BigDecimal> stockGraph;

    private final List<ColoredText> stockList = new ArrayList<>();
    private final Map<String, StockEntry> stockCache = new HashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Testing purposes
        stockList.add(TextFactory.createText("MSFT"));
        stockList.add(TextFactory.createText("AMZN"));
        stockList.add(TextFactory.createText("TSLA"));

        stocks.getItems().addAll(stockList);
        graphTitle.setText(stockList.get(0).getText());

        // Set colors for ListView on the side
        stocks.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(ColoredText item, boolean empty) {
                super.updateItem(item, empty);
                if(item == null) {
                    setText(null);
                    setTextFill(null);
                } else {
                    setText(item.getText());
                    setTextFill(item.getColor());
                }
            }
        });

        // So that animations don't cause anything to break when switching graphs
        stockGraph.setAnimated(false);

        // Add items to graph for first stock in ListView (default view)
        StockEntry firstStock = null;
        try {
            String symbol = stockList.get(0).getText();
            firstStock = new StockEntry(symbol);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Change this
        assert firstStock != null;
        stockGraph.getData().addAll(firstStock.getData());

        // Set graph label text when a different item is picked
        stocks.getSelectionModel().selectedItemProperty().addListener((observableValue, coloredText, t1) -> {
            // For setting top-left title
            String symbol = stocks.getSelectionModel().getSelectedItem().getText();
            graphTitle.setText(symbol);

            stockGraph.getData().clear();

            // Use cached data if it exists
            if(stockCache.containsKey(symbol)) {
                stockGraph.getData().addAll(stockCache.get(symbol).getData());
                return;
            }

            // Otherwise, just get data from the Yahoo Finance API
            try {
                StockEntry currStock = new StockEntry(symbol);
                stockGraph.getData().addAll(currStock.getData());
                stockCache.put(symbol, currStock);
            } catch (IOException e) {
                e.printStackTrace();
            }
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