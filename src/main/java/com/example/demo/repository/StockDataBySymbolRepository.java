package com.example.demo.repository;

import com.example.demo.model.StockDataBySymbol;
import com.example.demo.model.StockDataBySymbolKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface StockDataBySymbolRepository extends CassandraRepository<StockDataBySymbol, StockDataBySymbolKey> {

}