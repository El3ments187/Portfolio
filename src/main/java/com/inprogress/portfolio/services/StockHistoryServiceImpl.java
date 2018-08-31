package com.inprogress.portfolio.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inprogress.portfolio.commands.StockCommand;
import com.inprogress.portfolio.domain.Stock;
import com.inprogress.portfolio.domain.StockHistory;
import com.inprogress.portfolio.repositories.StockHistoryRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by David Munsell on 7/31/18.
 */
@Slf4j
@Service
public class StockHistoryServiceImpl implements StockHistoryService {

    private final StockHistoryRepository stockHistoryRepository;

    public StockHistoryServiceImpl(StockHistoryRepository stockHistoryRepository) {
        this.stockHistoryRepository = stockHistoryRepository;
    }

/*    @Override
    public Set<StockHistory> getHistoryForStock(Stock stock) {
        log.debug("StockHistory service reached.");
        
        String stockSymbol = stock.getStockSymbol();

        Set<StockHistory> stockHistorySet = new HashSet<>();
        stockHistoryRepository.findByStockSybmol(stockSymbol).iterator().forEachRemaining(stockHistorySet::add);
        return stockHistorySet;
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

	@Override
	public Set<Stock> getStockList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<StockHistory> getHistoryForStockSymbol() {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public StockHistory findById(Long l) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockHistory saveStockHistory(StockHistory stockHistory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockHistory getTodaysPrice(Stock stock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockHistory getYesterdaysPrice(Stock stock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<StockHistory> getHistoryForStock(Stock stock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Long idToDelete) {
		// TODO Auto-generated method stub
		
	}
}
