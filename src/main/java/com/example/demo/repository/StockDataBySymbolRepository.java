package com.example.demo.repository;

import com.example.demo.model.StockDataBySymbolKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.MapIdCassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.sql.Timestamp;
import java.util.Optional;

import com.example.demo.model.StockDataBySymbol;
import org.springframework.data.repository.CrudRepository;

public interface StockDataBySymbolRepository extends CassandraRepository<StockDataBySymbol, StockDataBySymbolKey> {

}