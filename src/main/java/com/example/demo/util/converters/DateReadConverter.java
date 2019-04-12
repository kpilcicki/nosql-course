package com.example.demo.util.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.sql.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

@ReadingConverter
public class DateReadConverter implements Converter<java.util.Date, Date> {

  public Date convert(java.util.Date source) {

    try {
      return new Date(source.getYear(), source.getMonth(), source.getDate()); //, source.getHours(), source.getMinutes(), source.getSeconds()
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }
}