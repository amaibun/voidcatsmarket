package com.amaibun.voidcatsmarket;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VoidcatsmarketApplication {
	@Autowired
	DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(VoidcatsmarketApplication.class, args);
	}

}
