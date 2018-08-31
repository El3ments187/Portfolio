package com.inprogress.portfolio.guidisplaypojos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockDisplayPojo {

	private String stockSymbol;
	private String companyName;
	private String lastPrice;
	private String tradeTimestamp;
	private String netChangeInDollars;
	private String percentChange;
	private String openPrice;
	private String daysHigh;
	private String daysLow;
	private String closePrice;
	private String fiftyTwoWeekHigh;
	private String fiftyTwoWeekHighDate;
	private String fiftyTwoWeekLow;
	private String fiftyTwoWeekLowDate;
	
	private String stockPurchasePrice;
	private String daysTotalGain;
	private String numberOfShares;
}
