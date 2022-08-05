package com.easyfin.constructs;

import javafx.scene.chart.XYChart;

/**
 * The StockEntry class holds all data relevant to a single
 * entry in the ListView used for displaying stocks a user
 * owns.
 */
public class StockEntry {
    // Definitions:
    // AF(x) is the abstraction function.
    // AF(this) = a name, [text], and list of (String, Integer) tuples, [data], such that
    //     text.getText() = the NYSE stock symbol of this entry
    //     text.getColor() = TEXT_GREY (defined in EntryMenuController)
    //     data = {} when there are no data-points
    //     data = {(S, I)} when there is a single datapoint
    //     data = {(S_0, I_0), (S_1, I_1), ... , (S_k, I_k)} for k data-points
    //
    // Representation Invariant:
    //     text != null &&
    //     text.getText() != null &&
    //     text.getColor() == TEXT_GREY &&
    //     data != null &&
    //     data.getData().size() >= 1

    private ColoredText text;
    private XYChart.Series<String, Integer> data; // monthly opening values

    public ColoredText getText() {
        return text;
    }

    public XYChart.Series<String, Integer> getData() {
        return data;
    }

    /**
     * Adds a datapoint to this stock entry.
     *
     * @param datapoint the data to be added
     */
    @SuppressWarnings("unchecked")
    public void addData(XYChart.Data<String, Integer> datapoint) {
        if (datapoint != null) {
            this.data.getData().addAll(datapoint);
        }
    }
}
