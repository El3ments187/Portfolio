package com.inprogress.portfolio.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by David Munsell on 8/28/18.
 */
@Getter
@Setter
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stockSymbol;
    private BigDecimal numberOfShares;
    private BigDecimal stockPurchasePrice;
    
    // Display only the stock symbol to make debugging easier
    @Override
    public String toString() {
    	return stockSymbol;
    }

}
