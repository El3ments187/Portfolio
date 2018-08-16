package com.inprogress.portfolio.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by jt on 6/13/17.
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
    private BigDecimal sharePurchasePrice;
    private BigDecimal shareCurrentPrice;
    
    //TODO: Remove
    private BigDecimal yesterdaysPrice;
    private BigDecimal changeDollars;
    private BigDecimal changePercent;
    private BigDecimal daysChangeDollars;
    private BigDecimal daysChangePercent;

}
