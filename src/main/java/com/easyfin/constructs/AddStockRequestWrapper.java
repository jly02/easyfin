package com.easyfin.constructs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AddStockRequestWrapper {
    private String symbol;
    private int amount;
}
