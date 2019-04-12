package com.example.demo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.cassandra.core.convert.CassandraCustomConversions;


// @EnableCassandraRepositories(repositoryFactoryBeanClass = MapIdCassandraRepository.class)
@SpringBootApplication
@EnableCassandraRepositories
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CassandraCustomConversions cassandraCustomConversions() {
			List<Converter<?, ?>> converters = new ArrayList<>();
			converters.add(new TimestampReadConverter());
			return new CassandraCustomConversions(converters);
	}

	static class TimestampReadConverter implements Converter<Date, Timestamp> {
		public Timestamp convert(Date source) {

			try {
				return new Timestamp(source.getTime()); //, source.getHours(), source.getMinutes(), source.getSeconds()
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
	}


	// static class DateReadConverter implements Converter<java.util.Date, Date> {
	// 	public Date convert(java.util.Date source) {

	// 		try {
	// 			return new Date(source.getTime()); //, source.getHours(), source.getMinutes(), source.getSeconds()
	// 		} catch (Exception e) {
	// 			throw new IllegalStateException(e);
	// 		}
	// 	}

	// }
	// static class TimestampReadConverter implements Converter<String, Timestamp> {
	// 	public Timestamp convert(String source) {

	// 		try {
	// 			DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss:sssss");
	// 			java.util.Date date = formatter.parse(source);
	// 			return new Timestamp(date.getTime()); //, source.getHours(), source.getMinutes(), source.getSeconds()
	// 		} catch (Exception e) {
	// 			throw new IllegalStateException(e);
	// 		}
	// 	}
	// }

	// static class TimestampWriteConverter implements Converter<Timestamp, String> {
	// 	public String convert(Timestamp source) {

	// 		try {
	// 			// DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
	// 			// Date date = formatter.parse(source.toString());
	// 			java.util.Date date = new java.util.Date();
	// 			date.setTime(source.getTime());
	// 			return new SimpleDateFormat("yyyy-mm-dd hh:mm:ssz").format(date);//, source.getHours(), source.getMinutes(), source.getSeconds()
	// 		} catch (Exception e) {
	// 			throw new IllegalStateException(e);
	// 		}
	// 	}
	// }
}
