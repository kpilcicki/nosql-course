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
@Table
public class StockDataBySymbol {

    @PrimaryKey
    private @NonNull StockDataBySymbolKey stockDataBySymbolKey;

    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Integer volume;

}
