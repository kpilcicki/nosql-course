package com.example.demo.repository;

import com.example.demo.model.StockCompany;
import com.example.demo.model.StockCompanyKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface StockCompanyRepository extends CassandraRepository<StockCompany, StockCompanyKey> {

}