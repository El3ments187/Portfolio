package com.inprogress.portfolio.services;


import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.inprogress.portfolio.commands.StockCommand;
import com.inprogress.portfolio.domain.Stock;

/**
 * Created by David Munsell on 8/28/18.
 */
public interface StockService {

    Set<Stock> getStocks();
    
    List<Stock> getStockList();

    Stock findById(Long l);

    StockCommand findCommandById(Long l);

    StockCommand saveStockCommand(StockCommand command);

    void deleteById(Long idToDelete);
    
    BigDecimal getStockCurrentPriceFromWeb(Long l);
    
    BigDecimal getStockYesterdaysPriceFromWeb(Long l);
    
}
