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
    private Integer numberOfShares;
    private BigDecimal stockPurchasePrice;
    private BigDecimal stockCurrentPrice;
    
    //TODO: Remove
    private BigDecimal yesterdaysPrice;
    private BigDecimal changeDollars;
    private BigDecimal changePercent;
    private BigDecimal daysChangeDollars;
    private BigDecimal daysChangePercent;

}
