package com.jvictor011.FoodMarketAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FoodMarketApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodMarketApiApplication.class, args);
	}

}
