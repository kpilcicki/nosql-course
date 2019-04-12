package com.example.demo.controller;

import com.example.demo.model.StockDataBySymbol;
import com.example.demo.repository.StockDataBySymbolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<StockDataBySymbol> dataBySymbol = stockDataBySymbolRepository.findById(stockQuery.getStockDataBySymbolKey());
        return dataBySymbol;
    }

    @PutMapping("/stockDataBySymbol")
    public Optional<StockDataBySymbol> updateStockDataBySymbol(@RequestBody StockDataBySymbol newStockDataBySymbol) {
        Optional<StockDataBySymbol> dataBySymbol = stockDataBySymbolRepository.findById(newStockDataBySymbol.getStockDataBySymbolKey());
        if (dataBySymbol.isPresent()) {
            StockDataBySymbol data = dataBySymbol.get();
            data.setOpen(newStockDataBySymbol.getOpen());
            data.setHigh(newStockDataBySymbol.getHigh());
            data.setLow(newStockDataBySymbol.getLow());
            data.setClose(newStockDataBySymbol.getClose());
            data.setVolume(newStockDataBySymbol.getVolume());

            stockDataBySymbolRepository.save(data);
        }
        return dataBySymbol;
    }

    @DeleteMapping(value = "/stockDataBySymbol", produces = "application/json; charset=utf-8")
    public String deleteStockDataBySymbolTimestampData(@RequestBody StockDataBySymbol newStockDataBySymbol) {
        Boolean result = stockDataBySymbolRepository.existsById(newStockDataBySymbol.getStockDataBySymbolKey());
        stockDataBySymbolRepository.deleteById(newStockDataBySymbol.getStockDataBySymbolKey());

        return "{ \"success\" : " + (result ? "true" : "false") + " }";
    }

    @PostMapping("/stockDataBySymbol")
    public StockDataBySymbol addStockDataBySymbol(@RequestBody StockDataBySymbol newStockDataBySymbol) {
        StockDataBySymbol dataBySymbol = new StockDataBySymbol(
                newStockDataBySymbol.getStockDataBySymbolKey(),
                newStockDataBySymbol.getOpen(),
                newStockDataBySymbol.getHigh(),
                newStockDataBySymbol.getLow(),
                newStockDataBySymbol.getClose(),
                newStockDataBySymbol.getVolume()
        );
        stockDataBySymbolRepository.save(dataBySymbol);
        return dataBySymbol;
    }
}