package com.inprogress.portfolio.services;


import java.util.Set;

import com.inprogress.portfolio.commands.StockCommand;
import com.inprogress.portfolio.domain.Stock;

/**
 * Created by jt on 6/13/17.
 */
public interface StockService {

    Set<Stock> getStocks();

    Stock findById(Long l);

    StockCommand findCommandById(Long l);

    StockCommand saveStockCommand(StockCommand command);

    void deleteById(Long idToDelete);
}
