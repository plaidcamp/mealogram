package com.plaidcamp.mealogram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties()
public class MealogramApiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealogramApiServerApplication.class, args);
	}

}
