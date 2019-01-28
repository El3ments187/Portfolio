package com.inprogress.portfolio.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inprogress.portfolio.commands.StockCommand;
import com.inprogress.portfolio.domain.Stock;
import com.inprogress.portfolio.services.StockService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping({"stock/add"})
    public String addStockPage(Model model) {
    	log.debug("Add a stock page");
    	
    	model.addAttribute("stock", new StockCommand());
    	
    	return "stock/add";
    }
    
    @PostMapping("stock")
    public String saveOrUpdate(@ModelAttribute StockCommand command){
    	log.debug("Attempting to save stock");
        StockCommand savedCommand = stockService.saveStockCommand(command);

        log.debug("Saved stock id:" + savedCommand.getId());

        return "redirect:/";
    }
    
    @RequestMapping({"stock/modify"})
    public String getPortfolioModificationPage(Model model) {
        log.debug("Getting Portfolio Modify page");

        Set<Stock> stocks = stockService.getStocks();
        
        log.debug("Stock List : " + stocks);
        
        //model.addAttribute("stocks", stocks);

        // Create a list of stocks so they can be sorted by stock symbol.
        List<Stock> stockList = new ArrayList<Stock>(stocks);
        stockList.sort(Comparator.comparing(Stock::getStockSymbol));
        
        model.addAttribute("stocks", stockList);

        return "stock/modify";
    }
    
    @RequestMapping({"stock/delete/{id}"})
    public String deleteStockPage(@PathVariable String id, Model model) {
    	log.debug("Delete a stock page - ID : " + id);
    	
    	model.addAttribute("stock", stockService.findById(new Long(id)));
    	
    	return "stock/delete";
    }
    
    @RequestMapping({"stock/delete/{id}/delete"})
    public String deleteStockById(@PathVariable String id, Model model) {
    	log.debug("Attempting to delete stock - Stock Id : " + id);
    	
    	stockService.deleteById(new Long(id));
    	
    	return "redirect:/stock/modify";
    }
    
}