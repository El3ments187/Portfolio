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
import com.inprogress.portfolio.services.StockService;

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

        Set<Stock> stocks = stockService.getStocks();
        
        log.debug("Stock List : " + stocks);
        
        //model.addAttribute("stocks", stocks);

        // Create a list of stocks so they can be sorted by stock symbol.
        List<Stock> stockList = new ArrayList<Stock>(stocks);
        stockList.sort(Comparator.comparing(Stock::getStockSymbol));
        
        for(Stock stock:stocks) {
        	BigDecimal purchase = stock.getSharePurchasePrice();
        	BigDecimal current = stock.getShareCurrentPrice();
        	
        	stock.setChangeDollars(purchase.subtract(current));
        	stock.setChangePercent(purchase.divide(current, BigDecimal.ROUND_HALF_UP));
        	
        	BigDecimal yesterday = stock.getYesterdaysPrice();
        	stock.setDaysChangeDollars(yesterday.subtract(current));
        	stock.setDaysChangePercent(yesterday.divide(current, BigDecimal.ROUND_HALF_UP));
        	//stockList.add(stock);
        }
        
        model.addAttribute("stocks", stockList);

        return "index";
    }
}
