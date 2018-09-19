package com.inprogress.portfolio.controllers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inprogress.portfolio.guidisplaypojos.StockDisplayPojo;
import com.inprogress.portfolio.services.StockQuoteCurrentServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by David Munsell on 7/31/18.
 */
@Slf4j
@Controller
public class IndexController {

    private final StockQuoteCurrentServiceImpl stockQuoteService;

    public IndexController(StockQuoteCurrentServiceImpl stockQuoteService) {
        this.stockQuoteService = stockQuoteService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index page");

        BigDecimal allStocksTotalGainInDollars = new BigDecimal(0);
        
        DecimalFormat dollarFormatter = new DecimalFormat("$#,##0.00");
        dollarFormatter.setParseBigDecimal(true);
        
        DecimalFormat shareFormatter = new DecimalFormat();
        shareFormatter.setDecimalSeparatorAlwaysShown(false);
        
        List<StockDisplayPojo> stockDisplayPojoList = stockQuoteService.getSortedListOfStockDisplayPojosForIndexPage();
        
        log.debug("Stock List : " + stockDisplayPojoList);
        
/*        for(Stock stock:stocks) {
        	StockDisplayPojo stockDisplay = new StockDisplayPojo();
        	
        	BarchartQuoteFetcher fetcher = new BarchartQuoteFetcher();
        	String[] stockQuoteArray = fetcher.fetchCurrentStockInformation(stock.getStockSymbol());
        	
        	BigDecimal netChangeInDollars = new BigDecimal(stockQuoteArray[4]);
        	BigDecimal currentNumberOfChares = stock.getNumberOfShares();
        	BigDecimal daysTotalGain = netChangeInDollars.multiply(currentNumberOfChares);
        	
        	allStocksTotalGainInDollars = allStocksTotalGainInDollars.add(daysTotalGain);
        	
        	stockDisplay.setStockSymbol(stock.getStockSymbol());
        	stockDisplay.setCompanyName(stockQuoteArray[1]);
        	stockDisplay.setNumberOfShares(shareFormatter.format(stock.getNumberOfShares()));
        	stockDisplay.setStockPurchasePrice(dollarFormatter.format(stock.getStockPurchasePrice()));
        	stockDisplay.setLastPrice(dollarFormatter.format(new BigDecimal(stockQuoteArray[2])));
        	stockDisplay.setNetChangeInDollars(dollarFormatter.format(new BigDecimal(stockQuoteArray[4])));
        	stockDisplay.setPercentChange(stockQuoteArray[5]);
        	
        	stockDisplay.setDaysTotalGain(dollarFormatter.format(daysTotalGain));
        	
        	stockDisplayList.add(stockDisplay);
        }*/
        
        stockDisplayPojoList.sort(Comparator.comparing(StockDisplayPojo::getStockSymbol));
        
        model.addAttribute("stocks", stockDisplayPojoList);
        
        allStocksTotalGainInDollars.setScale(2, BigDecimal.ROUND_HALF_UP);
        model.addAttribute("daysTotalGainDollars", dollarFormatter.format(allStocksTotalGainInDollars));
        
        log.debug("Day's Total Gain in Dollars : " + allStocksTotalGainInDollars.toString());

        return "index";
    }
}
