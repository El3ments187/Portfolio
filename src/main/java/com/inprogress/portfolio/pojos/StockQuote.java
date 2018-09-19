package com.inprogress.portfolio.pojos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockQuote {
	
	private String symbol;
	private String companyName;
	private Double lastPrice;
	private String tradeTimestamp;
	private BigDecimal netChange;
	private Double percentChange;
	private Double daysOpen;
	private Double daysHigh;
	private Double daysLow;		
	private Double closePrice;
	private Double fiftyTwoWkHigh;
	private String fiftyTwoWkHighDate;
	private Double fiftyTwoWkLow;
	private String fiftyTwoWkLowDate;
	private BigDecimal stockPurchasePrice;
	private BigDecimal numberOfShares;
	private BigDecimal daysTotalGain;

}
