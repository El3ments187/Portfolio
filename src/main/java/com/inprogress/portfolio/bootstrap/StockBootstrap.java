package com.inprogress.portfolio.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inprogress.portfolio.domain.Stock;
import com.inprogress.portfolio.repositories.StockRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by David Munsell on 7/31/18.
 */
@Slf4j
@Component
public class StockBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final StockRepository stockRepository;

    public StockBootstrap(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	stockRepository.saveAll(getStocks());
        log.debug("Loading Bootstrap Data");
    }

    private List<Stock> getStocks() {

        List<Stock> stocks = new ArrayList<>(2);

        Stock stock1 = new Stock();
        
        stock1.setStockSymbol("AAPL");
        stock1.setNumberOfShares(10);
        stock1.setStockPurchasePrice(new BigDecimal(13.20));
        //stock1.setStockCurrentPrice(new BigDecimal(15.23));
        //TODO
        stock1.setYesterdaysPrice(new BigDecimal(14.93));
        
        Stock stock2 = new Stock();
        
        stock2.setStockSymbol("F");
        stock2.setNumberOfShares(13);
        stock2.setStockPurchasePrice(new BigDecimal(138.21));
        //stock2.setStockCurrentPrice(new BigDecimal(122.89));
        //TODO
        stock2.setYesterdaysPrice(new BigDecimal(141.22));
        
        Stock stock3 = new Stock();
        
        stock3.setStockSymbol("BP");
        stock3.setNumberOfShares(1350);
        stock3.setStockPurchasePrice(new BigDecimal(60.21));
        //stock3.setStockCurrentPrice(new BigDecimal(72.49));
        //TODO
        stock3.setYesterdaysPrice(new BigDecimal(73.21));
        
        stocks.add(stock1);
        stocks.add(stock2);
        stocks.add(stock3);
        
        return stocks;
    }
        
        
/*    private List<StockHistory> getStockHistoryToday() {

        List<StockHistory> stockHistory = new ArrayList<>(2);

        StockHistory stock1 = new StockHistory();
        
        stock1.setStockSymbol("AAPL");
        stock1.setClosingPrice(new BigDecimal(15.34));
        stock1.setDate(LocalDate.now());

        StockHistory stock2 = new StockHistory();
        
        stock2.setStockSymbol("F");
        stock2.setClosingPrice(new BigDecimal(13.21));
        stock2.setDate(LocalDate.now());
        
        StockHistory stock3 = new StockHistory();
        
        stock3.setStockSymbol("BP");
        stock3.setClosingPrice(new BigDecimal(22.34));
        stock3.setDate(LocalDate.now());
        
        stockHistory.add(stock1);
        stockHistory.add(stock2);
        stockHistory.add(stock3);
        
        return stockHistory;
    }
    
    private List<StockHistory> getStockHistoryYesterday() {

        List<StockHistory> stockHistory = new ArrayList<>(2);
        
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minus(Period.ofDays(1));

        StockHistory stock1 = new StockHistory();
        
        stock1.setStockSymbol("AAPL");
        stock1.setClosingPrice(new BigDecimal(13));
        stock1.setDate(yesterday);

        StockHistory stock2 = new StockHistory();
        
        stock2.setStockSymbol("F");
        stock2.setClosingPrice(new BigDecimal(13));
        stock2.setDate(yesterday);
        
        StockHistory stock3 = new StockHistory();
        
        stock3.setStockSymbol("BP");
        stock3.setClosingPrice(new BigDecimal(13));
        stock3.setDate(yesterday);
        
        stockHistory.add(stock1);
        stockHistory.add(stock2);
        stockHistory.add(stock3);
        
        return stockHistory;
    }*/
}
