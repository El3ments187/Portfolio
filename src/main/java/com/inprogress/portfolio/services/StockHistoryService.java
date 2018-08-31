package com.inprogress.portfolio.services;


import java.util.Set;

import com.inprogress.portfolio.domain.Stock;
import com.inprogress.portfolio.domain.StockHistory;

/**
 * Created by David Munsell on 8/23/18.
 */
public interface StockHistoryService {

    Set<StockHistory> getHistoryForStock(Stock stock);

    StockHistory findById(Long l);

    StockHistory saveStockHistory(StockHistory stockHistory);

    void deleteById(Long idToDelete);
    
    StockHistory getTodaysPrice(Stock stock);
    
    StockHistory getYesterdaysPrice(Stock stock);
    
}
