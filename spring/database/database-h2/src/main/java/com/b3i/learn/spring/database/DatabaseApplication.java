package com.b3i.learn.spring.database;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Log
@SpringBootApplication
public class DatabaseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}

	private final DataSource dataSource;

	public DatabaseApplication(final DataSource dataSource) {this.dataSource = dataSource;}

	@Override
	public void run(final String... args){
		log.info("Datasource: " + dataSource.toString());
		final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
		restTemplate.execute("select 1");
	}
}
