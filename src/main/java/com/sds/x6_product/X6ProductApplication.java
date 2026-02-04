package com.sds.x6_product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableCaching
@SpringBootApplication
public class X6ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(X6ProductApplication.class, args);
	}

}
