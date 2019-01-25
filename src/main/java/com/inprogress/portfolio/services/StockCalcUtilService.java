package com.inprogress.portfolio.services;

import java.math.BigDecimal;
import java.util.List;

import com.inprogress.portfolio.guidisplaypojos.StockDisplayPojo;

public interface StockCalcUtilService {

    BigDecimal calculateTotalPortfolioGain(List<StockDisplayPojo> stockDisplayPojoList);
    
}