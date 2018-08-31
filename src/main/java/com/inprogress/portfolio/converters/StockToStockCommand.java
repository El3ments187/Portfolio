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
 * Created by David Munsell on 7/31/17.
 */
@Component
@NoArgsConstructor
@Slf4j
public class StockToStockCommand implements Converter<Stock, StockCommand>{

    @Synchronized
    @Nullable
    @Override
    public StockCommand convert(Stock source) {
    	log.debug("StockCommandToStock reached.");
        if (source == null) {
            return null;
        }

        final StockCommand command = new StockCommand();
        command.setId(source.getId());
        command.setStockSymbol(source.getStockSymbol());
        command.setNumberOfShares(source.getNumberOfShares());
        command.setStockPurchasePrice(source.getStockPurchasePrice());

        return command;
    }
}
