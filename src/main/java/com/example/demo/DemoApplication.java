package com.example.demo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.cassandra.core.convert.CassandraCustomConversions;
import org.springframework.data.cassandra.repository.MapIdCassandraRepository;


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
			converters.add(new DateReadConverter());
			return new CassandraCustomConversions(converters);
	}


	static class DateReadConverter implements Converter<java.util.Date, Date> {

		public Date convert(java.util.Date source) {

			try {
				// DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
				// Date date = formatter.parse(source.toString());
				return new Date(source.getTime()); //, source.getHours(), source.getMinutes(), source.getSeconds()
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
	}

}
