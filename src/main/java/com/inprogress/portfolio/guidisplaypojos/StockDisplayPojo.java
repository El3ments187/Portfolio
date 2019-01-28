package com.inprogress.portfolio.guidisplaypojos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDisplayPojo {

	private String stockSymbol;
	private String companyName;
	private BigDecimal lastPrice;
	private String tradeTimestamp;
	private BigDecimal netChangeInDollars;
	private BigDecimal percentChange;
	private BigDecimal openPrice;
	private BigDecimal daysHigh;
	private BigDecimal daysLow;
	private BigDecimal closePrice;
	private BigDecimal fiftyTwoWeekHigh;
	private String fiftyTwoWeekHighDate;
	private BigDecimal fiftyTwoWeekLow;
	private String fiftyTwoWeekLowDate;
	
	private BigDecimal stockPurchasePrice;
	private BigDecimal daysTotalGain;
	private BigDecimal numberOfShares;
	private BigDecimal totalGain;
	private BigDecimal percentChangeYTD;
	private BigDecimal dollarChangeYTD;
}
