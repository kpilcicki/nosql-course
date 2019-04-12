package com.example.demo.model;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import java.sql.Timestamp;

import com.datastax.driver.core.DataType.Name;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Table("Stock_Data_By_Symbol")
public class StockDataBySymbol {

  @PrimaryKeyColumn(
    name="symbol",
    type=PrimaryKeyType.PARTITIONED,
    ordinal=0
  )
  private @NonNull String symbol;

  @PrimaryKeyColumn(
    name="timestamp_data",
    type=PrimaryKeyType.CLUSTERED,
    ordering=Ordering.DESCENDING,
    ordinal=1
  )

  @CassandraType(type = Name.TIMESTAMP)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssz")
  private @NonNull Timestamp timestampData;

  private Double open;
  private Double high;
  private Double low;
  private Double close;
  private Integer volume;
  
}
