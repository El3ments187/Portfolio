package com.inprogress.portfolio.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inprogress.portfolio.guidisplaypojos.StockDisplayPojo;
import com.inprogress.portfolio.services.StockCalcUtilService;
import com.inprogress.portfolio.services.StockQuoteCurrentServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by David Munsell on 7/31/18.
 */
@Slf4j
@Controller
public class DetailedViewController {

    private final StockQuoteCurrentServiceImpl stockQuoteService;
    
    private final StockCalcUtilService stockCalcUtilService;

    public DetailedViewController(StockQuoteCurrentServiceImpl stockQuoteService, StockCalcUtilService stockCalcUtilService) {
        this.stockQuoteService = stockQuoteService;
        this.stockCalcUtilService = stockCalcUtilService;
    }

    @RequestMapping({"stock/detailed"})
    public String getPortfolioDetailedPage(Model model) {
        log.debug("Getting Detailed page");
        
        List<StockDisplayPojo> stockDisplayPojoList = stockQuoteService.getSortedListOfStockDisplayPojosForDisplayWithYTDGains();
        
        BigDecimal allStocksTotalGainInDollars = stockCalcUtilService.calculateTotalPortfolioGain(stockDisplayPojoList);
        
        log.debug("Stock List : " + stockDisplayPojoList);
        
        model.addAttribute("stocks", stockDisplayPojoList);
        
        model.addAttribute("daysTotalGainDollars", allStocksTotalGainInDollars);
        
        log.debug("Day's Total Gain in Dollars : " + allStocksTotalGainInDollars.toString());
        
        //BarchartHistoryFetcher history = new BarchartHistoryFetcher();
        
        //history.getHistoryForSymbolAndDate("BP", null, null, null);
        
        return "stock/detailed";
    }
}
