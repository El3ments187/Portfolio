package com.inprogress.portfolio.repositories;

import org.springframework.data.repository.CrudRepository;

import com.inprogress.portfolio.domain.Stock;

/**
 * Created by David Munsell on 7/31/18.
 */
public interface StockRepository extends CrudRepository<Stock, Long> {
}
