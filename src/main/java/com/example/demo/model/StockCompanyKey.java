package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

@PrimaryKeyClass
@Getter
@Setter
@AllArgsConstructor
public class StockCompanyKey implements Serializable {
    @PrimaryKeyColumn(
            name = "stock",
            ordinal = 0,
            type = PrimaryKeyType.PARTITIONED)
    private @NonNull String stock;

    @PrimaryKeyColumn(
            name = "symbol",
            ordinal = 1,
            ordering = Ordering.DESCENDING,
            type = PrimaryKeyType.CLUSTERED)
    private @NonNull String symbol;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof StockCompanyKey)) return false;

        StockCompanyKey stockKey = (StockCompanyKey) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(getStock(), stockKey.getStock())
                .append(getSymbol(), stockKey.getSymbol())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(getStock())
                .append(getSymbol())
                .toHashCode();
    }
}
