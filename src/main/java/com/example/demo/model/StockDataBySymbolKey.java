package com.example.demo.model;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@PrimaryKeyClass
public class StockDataBySymbolKey implements Serializable {
    @PrimaryKeyColumn(
            name = "symbol",
            type = PrimaryKeyType.PARTITIONED,
            ordinal = 0
    )
    private @NonNull String symbol;

    @PrimaryKeyColumn(
            name = "timestamp_data",
            type = PrimaryKeyType.CLUSTERED,
            ordering = Ordering.DESCENDING,
            ordinal = 1
    )
    @CassandraType(type = DataType.Name.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssz")
    private @NonNull Timestamp timestampData;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof StockDataBySymbolKey)) return false;

        StockDataBySymbolKey that = (StockDataBySymbolKey) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(getSymbol(), that.getSymbol())
                .append(getTimestampData(), that.getTimestampData())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(getSymbol())
                .append(getTimestampData())
                .toHashCode();
    }
}
