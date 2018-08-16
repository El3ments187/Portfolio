package com.inprogress.portfolio.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inprogress.portfolio.domain.Stock;
import com.inprogress.portfolio.repositories.StockRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by jt on 6/13/17.
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
        stock1.setSharePurchasePrice(new BigDecimal(13.20));
        stock1.setShareCurrentPrice(new BigDecimal(15.23));
        //TODO
        stock1.setYesterdaysPrice(new BigDecimal(14.93));
        
        Stock stock2 = new Stock();
        
        stock2.setStockSymbol("F");
        stock2.setNumberOfShares(13);
        stock2.setSharePurchasePrice(new BigDecimal(138.21));
        stock2.setShareCurrentPrice(new BigDecimal(122.89));
        //TODO
        stock2.setYesterdaysPrice(new BigDecimal(141.22));
        
        Stock stock3 = new Stock();
        
        stock3.setStockSymbol("BP");
        stock3.setNumberOfShares(1350);
        stock3.setSharePurchasePrice(new BigDecimal(60.21));
        stock3.setShareCurrentPrice(new BigDecimal(72.49));
        //TODO
        stock3.setYesterdaysPrice(new BigDecimal(73.21));
        
        stocks.add(stock1);
        stocks.add(stock2);
        stocks.add(stock3);
        
        return stocks;
    }
}
