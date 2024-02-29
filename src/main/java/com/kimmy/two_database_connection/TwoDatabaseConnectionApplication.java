package com.kimmy.two_database_connection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

@EnableR2dbcAuditing
@SpringBootApplication
public class TwoDatabaseConnectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwoDatabaseConnectionApplication.class, args);
	}

}
