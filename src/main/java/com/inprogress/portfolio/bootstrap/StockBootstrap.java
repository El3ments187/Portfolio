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
        
        stock1.setStockSymbol("ARKW");
        stock1.setNumberOfShares(new BigDecimal(35.0));
        stock1.setStockPurchasePrice(new BigDecimal(56.775));
        
        Stock stock2 = new Stock();
        
        stock2.setStockSymbol("BP");
        stock2.setNumberOfShares(new BigDecimal(60.0));
        stock2.setStockPurchasePrice(new BigDecimal(33.80));
        
        Stock stock3 = new Stock();
        
        stock3.setStockSymbol("CMG");
        stock3.setNumberOfShares(new BigDecimal(3));
        stock3.setStockPurchasePrice(new BigDecimal(367.3167));
        
        Stock stock4 = new Stock();
        
        stock4.setStockSymbol("EXPR");
        stock4.setNumberOfShares(new BigDecimal(660.0));
        stock4.setStockPurchasePrice(new BigDecimal(9.8806));
        
        Stock stock5 = new Stock();
        
        stock5.setStockSymbol("PBF");
        stock5.setNumberOfShares(new BigDecimal(231.76431));
        stock5.setStockPurchasePrice(new BigDecimal(22.802));
        
        Stock stock6 = new Stock();
        
        stock6.setStockSymbol("PSX");
        stock6.setNumberOfShares(new BigDecimal(51.87506));
        stock6.setStockPurchasePrice(new BigDecimal(72.8843));
        
        Stock stock7 = new Stock();
        
        stock7.setStockSymbol("UPRO");
        stock7.setNumberOfShares(new BigDecimal(105.0));
        stock7.setStockPurchasePrice(new BigDecimal(38.896));
        
        Stock stock8 = new Stock();
        
        stock8.setStockSymbol("VB");
        stock8.setNumberOfShares(new BigDecimal(7.14295));
        stock8.setStockPurchasePrice(new BigDecimal(109.2337));
        
        stocks.add(stock1);
        stocks.add(stock2);
        stocks.add(stock3);
        stocks.add(stock4);
        stocks.add(stock5);
        stocks.add(stock6);
        stocks.add(stock7);
        stocks.add(stock8);
        
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
