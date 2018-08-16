package com.inprogress.portfolio.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inprogress.portfolio.commands.StockCommand;
import com.inprogress.portfolio.converters.StockCommandToStock;
import com.inprogress.portfolio.converters.StockToStockCommand;
import com.inprogress.portfolio.domain.Stock;
import com.inprogress.portfolio.repositories.StockRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by David Munsell on 7/31/18.
 */
@Slf4j
@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final StockCommandToStock stockCommandToStock;
    private final StockToStockCommand stockToStockCommand;

    public StockServiceImpl(StockRepository stockRepository, StockCommandToStock stockCommandToStock, 
    		StockToStockCommand stockToStockCommand) {
        this.stockRepository = stockRepository;
        this.stockCommandToStock = stockCommandToStock;
        this.stockToStockCommand = stockToStockCommand;
    }

    @Override
    public Set<Stock> getStocks() {
        log.debug("Stock service reached.");

        Set<Stock> stockSet = new HashSet<>();
        stockRepository.findAll().iterator().forEachRemaining(stockSet::add);
        return stockSet;
    }

    @Override
    public Stock findById(Long l) {

        Optional<Stock> stockOptional = stockRepository.findById(l);

        if (!stockOptional.isPresent()) {
            log.error("stock Not Found. For ID value: " + l.toString());
        }

        return stockOptional.get();
    }

    @Override
    @Transactional
    public StockCommand findCommandById(Long l) {
        return stockToStockCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public StockCommand saveStockCommand(StockCommand command) {
        Stock detachedStock = stockCommandToStock.convert(command);

        Stock savedstock = stockRepository.save(detachedStock);
        log.debug("Saved stockId:" + savedstock.getId());
        return stockToStockCommand.convert(savedstock);
    }

    @Override
    public void deleteById(Long idToDelete) {
        stockRepository.deleteById(idToDelete);
    }
}
