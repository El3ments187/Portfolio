package com.inprogress.portfolio.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.inprogress.portfolio.domain.StockHistory;

/**
 * Created by David Munsell on 8/23/18.
 */
public interface StockHistoryRepository extends CrudRepository<StockHistory, Long> {

	@Query("SELECT a FROM StockHistory a WHERE a.stockSymbol = stockSymbol")
	@Transactional(readOnly = true)
	Set<StockHistory> findByStockSymbol(@Param("stockSymbol") String stockSymbol);
	
	
/*    @Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
    @Transactional(readOnly = true)
    List<PetType> findPetTypes();
    
    
    @Query("SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
    @Transactional(readOnly = true)
    Collection<Owner> findByLastName(@Param("lastName") String lastName);*/
}