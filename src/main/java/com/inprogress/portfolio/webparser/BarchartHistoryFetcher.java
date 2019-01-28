package com.inprogress.portfolio.webparser;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.barchart.ondemand.BarchartOnDemandClient;
import com.barchart.ondemand.api.HistoryRequest;
import com.barchart.ondemand.api.HistoryRequest.HistoryRequestType;
import com.barchart.ondemand.api.responses.History;
import com.barchart.ondemand.api.responses.HistoryBar;
import com.inprogress.portfolio.guidisplaypojos.StockDisplayPojo;

@Service
public class BarchartHistoryFetcher {
	
	//TODO : Move this to it's own class
	String apiKey = "7256b6b69d37a29bccceacc5dc039f1e";
	private final BarchartOnDemandClient onDemand = new BarchartOnDemandClient.Builder().apiKey(apiKey)
			.baseUrl("https://marketdata.websol.barchart.com/").build();
	
	public StockDisplayPojo getYTDGains(StockDisplayPojo displayPojo, Boolean dividends) {
		DateTime startDate = new DateTime().withDayOfYear(2).withTimeAtStartOfDay();
		
		History history = getHistoryForSymbolAndDate(displayPojo.getStockSymbol(), startDate, startDate, dividends);
		
		//TODO add logging
		if(history == null) {
			return null;
		}
		
		Collection<HistoryBar> bars = history.all();
		
		if(bars == null) {
			return null;
		}
		
		Double firstOfYearPrice = new Double(-1);
		for(HistoryBar historyBar : bars) {
			DateTime historyDate = new DateTime(historyBar.getTradingDay());
			if(startDate.equals(historyDate)) {
				firstOfYearPrice = historyBar.getClose();
				break;
			}
		}
		BigDecimal firstOfYearPriceBigDecimal = new BigDecimal(firstOfYearPrice);
		
		BigDecimal currentPrice = displayPojo.getLastPrice();
		
		BigDecimal changeYTD = currentPrice.subtract(firstOfYearPriceBigDecimal);
		
		BigDecimal numberOfShares = displayPojo.getNumberOfShares();
		
		BigDecimal dollarChangeYTD = changeYTD.multiply(numberOfShares);
		
		BigDecimal perchangeChangeYTD = currentPrice.divide(firstOfYearPriceBigDecimal, 2, RoundingMode.HALF_UP).subtract(new BigDecimal(1));
		
		displayPojo.setDollarChangeYTD(dollarChangeYTD);
		displayPojo.setPercentChangeYTD(perchangeChangeYTD);
		
		
		System.err.println("Stock Symbol : " + displayPojo.getStockSymbol() + " - YTD Gain $ : " + displayPojo.getDollarChangeYTD() + " - YTD Gain % : " + displayPojo.getPercentChangeYTD());

		return displayPojo;
		
	}
	
	private History getHistoryForSymbolAndDate(String stockSymbol, DateTime startDate, DateTime endDate, Boolean dividends) {
/*		DateTime endDate = DateTime.now();
		DateTime startDate = endDate.minusWeeks(1);*/
		
    	// Create a new HistoryRequest
    	final HistoryRequest.Builder builder = new HistoryRequest.Builder();
    	
    	builder.type(HistoryRequestType.DAILY);
    	
    	builder.start(startDate);
    	builder.end(endDate);

    	builder.dividends(dividends);

    	builder.symbol(stockSymbol);
    	
    	// Fetch results
    	History quote = null;
    	try {
			quote = onDemand.fetch(builder.build());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	System.err.println("History Quote : " + quote);
    	
    	return quote;
	}

}
