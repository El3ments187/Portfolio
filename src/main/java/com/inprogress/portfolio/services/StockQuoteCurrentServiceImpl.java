package com.inprogress.portfolio.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inprogress.portfolio.domain.Stock;
import com.inprogress.portfolio.guidisplaypojos.StockDisplayPojo;
import com.inprogress.portfolio.pojos.StockQuote;
import com.inprogress.portfolio.webparser.BarchartHistoryFetcher;
import com.inprogress.portfolio.webparser.BarchartQuoteFetcher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockQuoteCurrentServiceImpl implements StockQuoteCurrentService {
	
	private StockService stockService;
	
	private BarchartQuoteFetcher barchartQuoteFetcher;
	
	private BarchartHistoryFetcher barchartHistoryFetcher;
	
	@Autowired
	public StockQuoteCurrentServiceImpl(StockService stockService, BarchartQuoteFetcher barchartQuoteFetcher, 
			BarchartHistoryFetcher barchartHistoryFetcher) {
		this.stockService = stockService;
		this.barchartQuoteFetcher = barchartQuoteFetcher;
		this.barchartHistoryFetcher = barchartHistoryFetcher;
	}
	
/*	@Override
	public List<StockQuote> getStockQuotes(String[] stockTickerArray) {
		log.debug("StockQuoteCurrentService - Ticker Array : " + stockTickerArray);
		return fetcher.fetchMultpleCurrentStockInformation(stockTickerArray);
	}*/

/*	@Override
	public List<StockQuote> getFetchCurrentStockQuotes(List<Stock> stockList) {
		log.debug("StockQuoteCurrentService - Ticker List : " + stockList);
		
		// TODO : This will need refactored in the future to deal with more than 20 stock tickers
		String[] stockTickerArray = new String[20];
		for(int i = 0; i < stockList.size();) {
			stockTickerArray[i] = stockList.get(i).getStockSymbol();
			
		}
		return fetcher.fetchMultpleCurrentStockInformation(stockTickerArray);
	}*/
	
	private List<Stock> getListOfStocks() {
		List<Stock> stocks = stockService.getStockList();
		return stocks;
	}
	
	@Override
	public List<StockDisplayPojo> getSortedListOfStockDisplayPojosForDisplay(){
		
		List<Stock> stocks = getListOfStocks();
		
		List<StockDisplayPojo> stockDisplayPojoList = createStockDisplayPojoQuotes(stocks);
		
		stockDisplayPojoList.sort(Comparator.comparing(StockDisplayPojo::getStockSymbol));
				
		return stockDisplayPojoList;
	}
	
	@Override
	public List<StockDisplayPojo> getSortedListOfStockDisplayPojosForDisplayWithYTDGains(){
		
		List<Stock> stocks = getListOfStocks();
		
		List<StockDisplayPojo> stockDisplayPojoList = createStockDisplayPojoQuotes(stocks);
		
		stockDisplayPojoList = addYTDGainsToPojos(stockDisplayPojoList);
		
		if(stockDisplayPojoList != null) {
			stockDisplayPojoList.sort(Comparator.comparing(StockDisplayPojo::getStockSymbol));
		}
		
		return stockDisplayPojoList;
	}
	
	@Override
	public List<StockDisplayPojo> createStockDisplayPojoQuotes(List<Stock> stockList) {
		log.debug("createStockDisplayPojoQuotes reached.");
		
		List<StockQuote> stockQuotes = barchartQuoteFetcher.fetchMultpleCurrentStockInformation(stockList);
		
		List<StockDisplayPojo> stockDisplayPojoList = new ArrayList<StockDisplayPojo>();
		
		for(StockQuote quote : stockQuotes) {
			StockDisplayPojo stockDisplay = new StockDisplayPojo();
			
			stockDisplay.setStockSymbol(quote.getSymbol());
			stockDisplay.setCompanyName(quote.getCompanyName());
			stockDisplay.setLastPrice(new BigDecimal(quote.getLastPrice()));
			stockDisplay.setTradeTimestamp(quote.getTradeTimestamp());
			stockDisplay.setNetChangeInDollars(quote.getNetChange());
			stockDisplay.setPercentChange(new BigDecimal(quote.getPercentChange()));
			stockDisplay.setOpenPrice(new BigDecimal(quote.getDaysOpen()));
			stockDisplay.setDaysHigh(new BigDecimal(quote.getDaysHigh()));
			stockDisplay.setDaysLow(new BigDecimal(quote.getDaysLow()));
			stockDisplay.setClosePrice(new BigDecimal(quote.getClosePrice()));
			stockDisplay.setFiftyTwoWeekHigh(new BigDecimal(quote.getFiftyTwoWkHigh()));
			stockDisplay.setFiftyTwoWeekHighDate(quote.getFiftyTwoWkHighDate());
			stockDisplay.setFiftyTwoWeekLow(new BigDecimal(quote.getFiftyTwoWkLow()));
			stockDisplay.setFiftyTwoWeekLowDate(quote.getFiftyTwoWkLowDate());
			
			stockDisplay.setStockPurchasePrice(quote.getStockPurchasePrice());
			stockDisplay.setNumberOfShares(quote.getNumberOfShares());
			stockDisplay.setDaysTotalGain(quote.getDaysTotalGain());
			
			stockDisplayPojoList.add(stockDisplay);
			
			log.debug("StockDisplayPojo created in StockQuoteCurrentServiceImpl - " + stockDisplay.getStockSymbol());
		}
		
		return stockDisplayPojoList;
	}
	
	private List<StockDisplayPojo> addYTDGainsToPojos(List<StockDisplayPojo> stockDisplayPojoList) {
		List<StockDisplayPojo> pojoList = new ArrayList<>();
		
		for(StockDisplayPojo displayPojo : stockDisplayPojoList) {
			pojoList.add(barchartHistoryFetcher.getYTDGains(displayPojo, false));
		}
		
		return pojoList;
	}

}
