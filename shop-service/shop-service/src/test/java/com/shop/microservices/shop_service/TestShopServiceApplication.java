package com.shop.microservices.shop_service;

import org.springframework.boot.SpringApplication;

public class TestShopServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ShopServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
