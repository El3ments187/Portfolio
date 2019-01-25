package com.inprogress.portfolio.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inprogress.portfolio.guidisplaypojos.StockDisplayPojo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockCalcUtilServiceImpl implements StockCalcUtilService {

	@Override
	public BigDecimal calculateTotalPortfolioGain(List<StockDisplayPojo> stockDisplayPojoList) {
		BigDecimal totalStocksGain = new BigDecimal(0);
		for(StockDisplayPojo stockDisplayPojo : stockDisplayPojoList) {
			if(stockDisplayPojo.getDaysTotalGain() != null) {
				totalStocksGain = totalStocksGain.add(stockDisplayPojo.getDaysTotalGain());
			}
		}
		return totalStocksGain;
	}


}
