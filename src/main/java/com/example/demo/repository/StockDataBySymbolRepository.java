package com.example.demo.repository;

import org.springframework.data.cassandra.repository.MapIdCassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.sql.Date;
import java.util.Optional;

import com.example.demo.model.StockDataBySymbol;

public interface StockDataBySymbolRepository extends MapIdCassandraRepository<StockDataBySymbol> {

  Optional<StockDataBySymbol> findBySymbol(String symbol);
  
  @Query("SELECT*FROM teststockspace.stock_data_by_symbol WHERE symbol=?0 AND timestamp_data=?1")
  Optional<StockDataBySymbol> findBySymbolAndTimestampData(String symbol, Date timestampData);

  @Query("SELECT*FROM teststockspace.stock_data_by_symbol WHERE symbol=?0 AND timestamp_data=?1")
  Boolean existsBySymbolAndTimestampData(String symbol, Date timestampData);
  
  @Query("SELECT*FROM teststockspace.stock_data_by_symbol WHERE symbol=?0 AND timestamp_data=?1")
  long deleteBySymbolAndTimestampData(String symbol, Date timestampData);

	Boolean existsBySymbol(String symbol);
}