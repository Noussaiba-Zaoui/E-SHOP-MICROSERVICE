package com.shop.microservices.shop_service.repository;

import com.shop.microservices.shop_service.Model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item,String> {
}
