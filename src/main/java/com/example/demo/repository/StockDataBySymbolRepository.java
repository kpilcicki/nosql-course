package com.example.demo.repository;

import org.springframework.data.cassandra.repository.MapIdCassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.sql.Timestamp;
import java.util.Optional;

import com.example.demo.model.StockDataBySymbol;

public interface StockDataBySymbolRepository extends MapIdCassandraRepository<StockDataBySymbol> {

  Optional<StockDataBySymbol> findBySymbol(String symbol);
  
  Optional<StockDataBySymbol> findBySymbolAndTimestampData(String symbol, Timestamp timestampData);

  Boolean existsBySymbolAndTimestampData(String symbol, Timestamp timestampData);
  
  // derived one doesnt delete fsr
  @Query("DELETE FROM teststockspace.stock_data_by_symbol WHERE symbol=?0 AND timestamp_data=?1")
  Optional<StockDataBySymbol> deleteBySymbolAndTimestampData(String symbol, Timestamp timestampData);

	Boolean existsBySymbol(String symbol);
}