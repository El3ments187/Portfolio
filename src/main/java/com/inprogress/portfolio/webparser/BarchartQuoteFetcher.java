package com.inprogress.portfolio.webparser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.barchart.ondemand.BarchartOnDemandClient;
import com.barchart.ondemand.api.QuoteRequest;
import com.barchart.ondemand.api.QuoteRequest.QuoteRequestMode;
import com.barchart.ondemand.api.responses.Quote;
import com.barchart.ondemand.api.responses.Quotes;
import com.inprogress.portfolio.domain.Stock;
import com.inprogress.portfolio.pojos.StockQuote;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BarchartQuoteFetcher {
	
	String apiKey = "7256b6b69d37a29bccceacc5dc039f1e";
	private final BarchartOnDemandClient onDemand = new BarchartOnDemandClient.Builder().apiKey(apiKey)
			.baseUrl("https://marketdata.websol.barchart.com/").build();
    
/*    public String[] fetchCurrentStockInformation(String symbol) {
    	String[] quoteArray = new String[14];
    	
    	// Create a new QuoteRequest
    	final QuoteRequest.Builder builder = new QuoteRequest.Builder();
    	
    	// Add symbols to the request
    	builder.symbols(new String[] {symbol});
    	// Set mode to real-time
    	builder.mode(QuoteRequestMode.REAL_TIME);
    	
    	// Fetch results
    	try {
			final Quotes quotes = onDemand.fetch(builder.build());
			Quote quote = quotes.bySymbol(symbol);
			
			quoteArray[0] = quote.getSymbol();
			quoteArray[1] = quote.getName();
			quoteArray[2] = String.valueOf(quote.getLastPrice());
			quoteArray[3] = quote.getTradeTimestamp();
			quoteArray[4] = String.valueOf(quote.getNetChange());
			quoteArray[5] = String.valueOf(quote.getPercentChange());
			quoteArray[6] = String.valueOf(quote.getOpen());
			quoteArray[7] = String.valueOf(quote.getHigh());
			quoteArray[8] = String.valueOf(quote.getLow());		
			quoteArray[9] = String.valueOf(quote.getClose());
			quoteArray[10] = String.valueOf(quote.getFiftyTwoWkHigh());
			quoteArray[11] = quote.getFiftyTwoWkHighDate();
			quoteArray[12] = String.valueOf(quote.getFiftyTwoWkLow());
			quoteArray[13] = quote.getFiftyTwoWkLowDate();
					
					
			log.debug("Quote : " + quoteArray[2]);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return quoteArray;
    }*/
	
	private String[] convertListOfStocksToStringArrray(List<Stock> stockList) {

		String[] stockTickerArray = new String[stockList.size()];
		
		for(int i = 0; i < stockList.size(); i++) {
			String symbol = stockList.get(i).getStockSymbol();
			System.out.println("Symbol : " + symbol);
			stockTickerArray[i] = stockList.get(i).getStockSymbol();
		}
		
		return stockTickerArray;
	}
    
    public List<StockQuote> fetchMultpleCurrentStockInformation(List<Stock> stockList) {
    	
    	log.debug("fetchMultpleCurrentStockInformation reached.");
    	
    	// Create a new QuoteRequest
    	final QuoteRequest.Builder builder = new QuoteRequest.Builder();
    	
    	String[] symbols = convertListOfStocksToStringArrray(stockList);
    	
    	// Add symbols to the request
    	builder.symbols(symbols);
    	// Set mode to real-time
    	builder.mode(QuoteRequestMode.REAL_TIME);
    	
    	// Fetch results
    	Quotes quotes = null;
    	try {
			quotes = onDemand.fetch(builder.build());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	    	
    	List<StockQuote> stockQuotes = new ArrayList<StockQuote>();
    	
    	for (Stock stock : stockList) {
    		
    		String stockSymbol = stock.getStockSymbol();
    		
    		Quote quote = quotes.bySymbol(stockSymbol);
    		
    		StockQuote stockQuote = new StockQuote();
    		
    		stockQuote.setSymbol(stockSymbol);
    		stockQuote.setCompanyName(quote.getName());
    		stockQuote.setLastPrice(quote.getLastPrice());
    		stockQuote.setTradeTimestamp(quote.getTradeTimestamp());
    		
    		BigDecimal netChange = new BigDecimal(quote.getNetChange());
    		stockQuote.setNetChange(netChange);
    		stockQuote.setPercentChange(quote.getPercentChange());
    		stockQuote.setDaysOpen(quote.getOpen());
    		stockQuote.setDaysHigh(quote.getHigh());
    		stockQuote.setDaysLow(quote.getLow());		
    		stockQuote.setClosePrice(quote.getClose());
    		stockQuote.setFiftyTwoWkHigh(quote.getFiftyTwoWkHigh());
    		stockQuote.setFiftyTwoWkHighDate(quote.getFiftyTwoWkHighDate());
    		stockQuote.setFiftyTwoWkLow(quote.getFiftyTwoWkLow());
    		stockQuote.setFiftyTwoWkLowDate(quote.getFiftyTwoWkLowDate());
    		
    		BigDecimal numberOfShares = stock.getNumberOfShares();
    		stockQuote.setStockPurchasePrice(numberOfShares);
    		stockQuote.setNumberOfShares(stock.getNumberOfShares());
    		
    		// Calculating Day's Total Gain here to avoid additional processing for summaries
    		BigDecimal daysTotalGain = netChange.multiply(numberOfShares);
    		stockQuote.setDaysTotalGain(daysTotalGain);
    		
    		stockQuotes.add(stockQuote);
    		
    		log.debug("Quote - Symbol : " + stockQuote.getSymbol() + " Last Price : " + stockQuote.getLastPrice());
    	}

    	return stockQuotes;
    }
    
    // Saving code for now in case there are future issues with the Barchart API
    
    /*    public String[] fetchCurrentPriceQuote(String symbol) {
	
	
    String[] quoteArray = new String[3];

    try{
        URL barchartCurrentPrice = new URL("https://marketdata.websol.barchart.com/getQuote.csv?apikey=" + apiKey + "&symbols=" + symbol + 
        		"&fields=fiftyTwoWkHigh%2CfiftyTwoWkHighDate%2CfiftyTwoWkLow%2CfiftyTwoWkLowDate");
        log.debug(barchartCurrentPrice.toString());
        try{ 
            URLConnection conn = barchartCurrentPrice.openConnection();
            conn.connect();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
//            BufferedReader data = new BufferedReader(in);
            CSVParser parser = new CSVParser(in, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : parser) {
                quoteArray[0] = record.get("symbol");
                quoteArray[1] = record.get("tradeTimestamp");
                quoteArray[2] = record.get("lastPrice");
//                quoteRecord[3] = record.get("High");
//                quoteRecord[4] = record.get("Low");
//                quoteRecord[5] = record.get("Close");
//                quoteRecord[6] = record.get("Volume");
//                quoteRecord[7] = record.get("Adj Close");
              log.debug("Symbol : " + quoteArray[0] + " Date/Time : " + quoteArray[1] + " Last Price : " + quoteArray[2]);
            }
            if(!parser.isClosed()) {
            	parser.close();
            }
            return quoteArray;
            
		} catch (IOException e) {
		log.error("Error -- " + e.toString());
		} 
	} catch (MalformedURLException e){
		log.error("Error -- " + e.toString());
	}
	return quoteArray;
}*/

}
