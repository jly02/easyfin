package com.easyfin.constructs;

import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Month;
import java.util.Calendar;

/**
 * The StockEntry class holds all data relevant to a single
 * entry in the ListView used for displaying stocks a user
 * owns.
 */
public class StockEntry {
    // ================================================================================== \\
    // Definitions:                                                                       \\
    // AF(x) is the abstraction function.                                                 \\
    // AF(this) = a name, [text], and list of (String, Integer) tuples, [data], such that \\
    //     text.getText() = the NYSE stock symbol of this entry                           \\
    //     text.getColor() = TEXT_GREY (defined in EntryMenuController)                   \\
    //     data = {} when there are no data-points                                        \\
    //     data = {(S, I)} when there is a single datapoint                               \\
    //     data = {(S_0, I_0), (S_1, I_1), ... , (S_k, I_k)} for k data-points            \\
    //                                                                                    \\
    // Representation Invariant:                                                          \\
    //     text != null &&                                                                \\
    //     text.getText() != null &&                                                      \\
    //     text.getColor() == TEXT_GREY &&                                                \\
    //     data != null &&                                                                \\
    //     data.getData().size() >= 1                                                     \\
    // ================================================================================== \\

    private final String symbol;
    private final XYChart.Series<String, BigDecimal> data; // monthly closing values

    /**
     * Initializes a StockEntry object with all the data necessary for adding points
     * into a graph.
     *
     * @param symbol the stock symbol
     * @throws IOException if Yahoo Finance API does not successfully get stock data
     */
    public StockEntry(String symbol) throws IOException {
        this.symbol = symbol;
        this.data = new XYChart.Series<>();
        data.setName("Stock value over time");

        Calendar from = Calendar.getInstance();
        Calendar to = Calendar.getInstance();
        from.add(Calendar.MONTH, -36);
        Stock stock = YahooFinance.get(symbol, from, to, Interval.MONTHLY);

        Calendar date = Calendar.getInstance();
        for(HistoricalQuote hq : stock.getHistory()) {
            date.setTime(hq.getDate().getTime());
            String label = getMonth(date.get(Calendar.MONTH) + 1) + " " + date.get(Calendar.YEAR);
            BigDecimal closeVal = hq.getClose();
            data.getData().add(new XYChart.Data<>(label, closeVal));
        }
    }

    public String getSymbol() {
        return this.symbol;
    }

    public XYChart.Series<String, BigDecimal> getData() {
        return this.data;
    }

    /**
     * Adds a datapoint to this stock entry.
     *
     * @param label the x-axis label for this point
     * @param value the closing value of the stock on a given day
     */
    @SuppressWarnings("unchecked")
    public void addData(String label, BigDecimal value) {
        if(label.isBlank() || value.compareTo(BigDecimal.ZERO) < 0) {
            return;
        }

        XYChart.Data<String, BigDecimal> datapoint = new XYChart.Data<>(label, value);
        this.data.getData().addAll(datapoint);
    }

    /**
     * Adds a datapoint to this stock entry.
     *
     * @param month the integer representation for a month of the year, 0-11
     * @param value the closing value of the stock on a given day
     */
    @SuppressWarnings("unchecked")
    public void addData(int month, BigDecimal value) {
        if(month < 0 || value.compareTo(BigDecimal.ZERO) < 0) {
            return;
        }

        XYChart.Data<String, BigDecimal> datapoint = new XYChart.Data<>(getMonth(month), value);
        this.data.getData().addAll(datapoint);
    }

    // Returns the abbreviated name of a month given its integer value
    // takes values as 1-12
    private String getMonth(int month) {
        return Month.of(month).toString().substring(0, 3);
    }
}
