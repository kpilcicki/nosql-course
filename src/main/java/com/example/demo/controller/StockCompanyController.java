package com.example.demo.controller;

import com.example.demo.model.StockCompany;
import com.example.demo.repository.StockCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StockCompanyController {
    @Autowired
    StockCompanyRepository stockCompanyController;

    @GetMapping("/stockCompanies")
    public List<StockCompany> getStockCompanies() {
        Iterable<StockCompany> result = stockCompanyController.findAll();
        List<StockCompany> stockCompanies = new ArrayList<StockCompany>();
        result.forEach(stockCompanies::add);
        return stockCompanies;
    }

    @GetMapping("/stockCompany")
    public Optional<StockCompany> getStockCompanyByKey(@RequestBody StockCompany stockQuery) {
        Optional<StockCompany> stockCompany = stockCompanyController.findById(stockQuery.getKey());
        return stockCompany;
    }

    @PutMapping("/stockCompany")
    public Optional<StockCompany> updateStockCompany(@RequestBody StockCompany newStockCompany) {
        Optional<StockCompany> stockCompany = stockCompanyController.findById(newStockCompany.getKey());
        if (stockCompany.isPresent()) {
            StockCompany company = stockCompany.get();
            company.setCompany_name(newStockCompany.getCompany_name());

            stockCompanyController.save(company);
        }
        return stockCompany;
    }

    @DeleteMapping(value = "/stockCompany", produces = "application/json; charset=utf-8")
    public String deleteStockCompany(@RequestBody StockCompany stockCompany) {
        Boolean result = stockCompanyController.existsById(stockCompany.getKey());
        stockCompanyController.deleteById(stockCompany.getKey());

        return "{ \"success\" : " + (result ? "true" : "false") + " }";
    }

    @PostMapping("/stockCompany")
    public StockCompany addStockDataBySymbol(@RequestBody StockCompany newStockCompany) {
        StockCompany dataBySymbol = new StockCompany(
                newStockCompany.getKey(),
                newStockCompany.getCompany_name()
        );
        stockCompanyController.save(dataBySymbol);
        return dataBySymbol;
    }
}