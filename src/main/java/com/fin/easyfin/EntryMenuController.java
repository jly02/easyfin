package com.fin.easyfin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class EntryMenuController implements Initializable {
    // Color used for text in left list view
    private static final Color TEXT_GREY = new Color(
            (double) 0xbf / 0x100,
            (double) 0xbf / 0x100,
            (double) 0xbf/ 0x100,
            1
    );

    @FXML private ListView<ColoredText> stocks;
    @FXML private Label graphTitle;

    List<ColoredText> stockList = new ArrayList<>();

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

        // Set graph label text when a different item is picked
        stocks.getSelectionModel().selectedItemProperty().addListener((observableValue, coloredText, t1) -> {
            String newTitle = stocks.getSelectionModel().getSelectedItem().getText();
            graphTitle.setText(newTitle);
        });
    }
}