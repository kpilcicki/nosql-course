package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.StockDataBySymbol;
import com.example.demo.repository.StockDataBySymbolRepository;

@RestController
public class StockDataBySymbolController {
    @Autowired
    StockDataBySymbolRepository stockDataBySymbolRepository;

    @GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
    public String getHealthCheck() {
        return "{ \"isWorking\" : true }";
    }

    @GetMapping("/stockDataBySymbols")
    public List<StockDataBySymbol> getStockDataBySymbols() {
        Iterable<StockDataBySymbol> result = stockDataBySymbolRepository.findAll();
        List<StockDataBySymbol> stockDataBySymbolsList = new ArrayList<StockDataBySymbol>();
        result.forEach(stockDataBySymbolsList::add);
        return stockDataBySymbolsList;
    }

    @GetMapping("/stockDataBySymbolKey")
    public Optional<StockDataBySymbol> getStockDataBySymbolAndTimestamp(@RequestBody StockDataBySymbol stockQuery) {
        Optional<StockDataBySymbol> emp = stockDataBySymbolRepository.findById(stockQuery.getStockDataBySymbolKey());
        return emp;
    }

    @PutMapping("/stockDataBySymbol")
    public Optional<StockDataBySymbol> updateStockDataBySymbol(@RequestBody StockDataBySymbol newStockDataBySymbol) {
        Optional<StockDataBySymbol> optionalEmp = stockDataBySymbolRepository.findById(newStockDataBySymbol.getStockDataBySymbolKey());
        if (optionalEmp.isPresent()) {
            StockDataBySymbol emp = optionalEmp.get();
            emp.setOpen(newStockDataBySymbol.getOpen());
            emp.setHigh(newStockDataBySymbol.getHigh());
            emp.setLow(newStockDataBySymbol.getLow());
            emp.setClose(newStockDataBySymbol.getClose());
            emp.setVolume(newStockDataBySymbol.getVolume());

            stockDataBySymbolRepository.save(emp);
        }
        return optionalEmp;
    }

    @DeleteMapping(value = "/stockDataBySymbol", produces = "application/json; charset=utf-8")
    public String deleteStockDataBySymbolTimestampData(@RequestBody StockDataBySymbol newStockDataBySymbol) {
        Boolean result = stockDataBySymbolRepository.existsById(newStockDataBySymbol.getStockDataBySymbolKey());
        stockDataBySymbolRepository.deleteById(newStockDataBySymbol.getStockDataBySymbolKey());

        return "{ \"success\" : " + (result ? "true" : "false") + " }";
    }

    @PostMapping("/stockDataBySymbol")
    public StockDataBySymbol addStockDataBySymbol(@RequestBody StockDataBySymbol newStockDataBySymbol) {
        StockDataBySymbol emp = new StockDataBySymbol(
                newStockDataBySymbol.getStockDataBySymbolKey(),
                newStockDataBySymbol.getOpen(),
                newStockDataBySymbol.getHigh(),
                newStockDataBySymbol.getLow(),
                newStockDataBySymbol.getClose(),
                newStockDataBySymbol.getVolume()
        );
        stockDataBySymbolRepository.save(emp);
        return emp;
    }
}