package com.inprogress.portfolio.converters;


import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.inprogress.portfolio.commands.StockCommand;
import com.inprogress.portfolio.domain.Stock;

import lombok.NoArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jt on 6/21/17.
 */
@Component
@NoArgsConstructor
@Slf4j
public class StockCommandToStock implements Converter<StockCommand, Stock> {

    @Synchronized
    @Nullable
	@Override
	public Stock convert(StockCommand source) {
    	log.debug("StockCommandToStock reached.");
    	
        if (source == null) {
            return null;
        }
        
        final Stock stock = new Stock();
        stock.setId(source.getId());
        stock.setStockSymbol(source.getStockSymbol());
        stock.setNumberOfShares(source.getNumberOfShares());
        stock.setSharePurchasePrice(source.getSharePurchasePrice());
        stock.setShareCurrentPrice(source.getShareCurrentPrice());
        
		return stock;
	}
}
