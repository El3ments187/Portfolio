package com.inprogress.portfolio.services;

import java.util.List;

import com.inprogress.portfolio.domain.Stock;
import com.inprogress.portfolio.guidisplaypojos.StockDisplayPojo;

public interface StockQuoteCurrentService {
	
	public List<StockDisplayPojo> getSortedListOfStockDisplayPojosForDisplay();

	public List<StockDisplayPojo> createStockDisplayPojoQuotes(List<Stock> stockList);

	public List<StockDisplayPojo> getSortedListOfStockDisplayPojosForDisplayWithYTDGains();
}
