package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@AllArgsConstructor
@Getter
@Setter
@Table("Stock_Companies")
public class StockCompany {
    @PrimaryKey
    private @NonNull StockCompanyKey key;
    private String company_name;
}
