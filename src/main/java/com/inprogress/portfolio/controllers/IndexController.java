package com.inprogress.portfolio.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inprogress.portfolio.domain.Stock;
import com.inprogress.portfolio.guidisplaypojos.StockDisplayPojo;
import com.inprogress.portfolio.services.StockService;
import com.inprogress.portfolio.webparser.BarchartQuoteFetcher;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by David Munsell on 7/31/18.
 */
@Slf4j
@Controller
public class IndexController {

    private final StockService stockService;

    public IndexController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index page");
        
        List<StockDisplayPojo> stockDisplayList = new ArrayList<StockDisplayPojo>();

        Set<Stock> stocks = stockService.getStocks();
        
        BigDecimal allStocksTotalGainInDollars = new BigDecimal(0);
        
        log.debug("Stock List : " + stocks);
        
        for(Stock stock:stocks) {
        	StockDisplayPojo stockDisplay = new StockDisplayPojo();
        	
        	BarchartQuoteFetcher fetcher = new BarchartQuoteFetcher();
        	String[] stockQuoteArray = fetcher.fetchCurrentPrice(stock.getStockSymbol());
        	
        	BigDecimal netChangeInDollars = new BigDecimal(stockQuoteArray[4]);
        	BigDecimal currentNumberOfChares = new BigDecimal(stock.getNumberOfShares());
        	BigDecimal daysTotalGain = netChangeInDollars.multiply(currentNumberOfChares);
        	
        	allStocksTotalGainInDollars = allStocksTotalGainInDollars.add(daysTotalGain);
        	
        	stockDisplay.setStockSymbol(stock.getStockSymbol());
        	stockDisplay.setCompanyName(stockQuoteArray[1]);
        	stockDisplay.setNumberOfShares(String.valueOf(stock.getNumberOfShares()));
        	stockDisplay.setStockPurchasePrice(String.valueOf(stock.getStockPurchasePrice()));
        	stockDisplay.setLastPrice(stockQuoteArray[2]);
        	stockDisplay.setNetChangeInDollars(stockQuoteArray[4]);
        	stockDisplay.setPercentChange(stockQuoteArray[5]);
        	stockDisplay.setDaysTotalGain(String.valueOf(daysTotalGain));
        	
        	stockDisplayList.add(stockDisplay);
        }
        
        stockDisplayList.sort(Comparator.comparing(StockDisplayPojo::getStockSymbol));
        
        model.addAttribute("stocks", stockDisplayList);
        model.addAttribute("daysTotalGainDollars", allStocksTotalGainInDollars);
        
        log.debug("Day's Total Gain in Dollars : " + allStocksTotalGainInDollars.toString());

        return "index";
    }
}
