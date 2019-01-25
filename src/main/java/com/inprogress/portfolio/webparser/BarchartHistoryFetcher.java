package com.inprogress.portfolio.webparser;

import org.joda.time.DateTime;

import com.barchart.ondemand.BarchartOnDemandClient;
import com.barchart.ondemand.api.HistoryRequest;
import com.barchart.ondemand.api.HistoryRequest.HistoryRequestType;
import com.barchart.ondemand.api.responses.History;

public class BarchartHistoryFetcher {
	
	String apiKey = "7256b6b69d37a29bccceacc5dc039f1e";
	private final BarchartOnDemandClient onDemand = new BarchartOnDemandClient.Builder().apiKey(apiKey)
			.baseUrl("https://marketdata.websol.barchart.com/").build();
	
	public void getHistoryForSpecifiedDateAndSymbol(String stockSymbol) {
		DateTime endDate = DateTime.now();
		DateTime startDate = endDate.minusWeeks(1);
				
		Boolean dividends = false;
		
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
	}

}
