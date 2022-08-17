package com.easyfin.constructs;

import lombok.Getter;

import java.util.List;

@Getter
public class GetStocksResponse {
    private List<Stock> response;
    private String message;

    @Getter
    public static class Stock {
        private String symbol;
        private int amount;
    }
}
