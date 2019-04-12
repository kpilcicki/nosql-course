package com.example.demo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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

  @GetMapping("/stockDataBySymbolTimestamp")
  public Optional<StockDataBySymbol> getStockDataBySymbolAndTimestamp(@RequestBody StockDataBySymbol stockQuery){
    String symbol = stockQuery.getSymbol();
    Date timestampData = stockQuery.getTimestampData();
    Optional<StockDataBySymbol> emp = stockDataBySymbolRepository.findBySymbolAndTimestampData(symbol, timestampData);
    return emp;
  }

  @PutMapping("/stockDataBySymbol")
  public Optional<StockDataBySymbol> updateStockDataBySymbol(@RequestBody StockDataBySymbol newStockDataBySymbol) {
    String symbol = newStockDataBySymbol.getSymbol();
    Date timestampData = newStockDataBySymbol.getTimestampData();
    Optional<StockDataBySymbol> optionalEmp = stockDataBySymbolRepository.findBySymbolAndTimestampData(symbol, timestampData);
    if (optionalEmp.isPresent()) {
      StockDataBySymbol emp = optionalEmp.get();
      // emp.setTimestampData(newStockDataBySymbol.getTimestampData());
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
    String symbol = newStockDataBySymbol.getSymbol();
    Date timestampData = newStockDataBySymbol.getTimestampData();
    Boolean result = stockDataBySymbolRepository.existsBySymbolAndTimestampData(symbol, timestampData);
    stockDataBySymbolRepository.deleteBySymbolAndTimestampData(symbol, timestampData);
    return "{ \"success\" : " + (result ? "true" : "false") + " }";
  }

  @PostMapping("/stockDataBySymbol")
  public StockDataBySymbol addStockDataBySymbol(@RequestBody StockDataBySymbol newStockDataBySymbol) {
    StockDataBySymbol emp = new StockDataBySymbol(
      newStockDataBySymbol.getSymbol(),
      newStockDataBySymbol.getTimestampData(),
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