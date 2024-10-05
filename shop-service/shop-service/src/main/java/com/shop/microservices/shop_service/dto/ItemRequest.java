package com.shop.microservices.shop_service.dto;

import java.math.BigDecimal;

public record ItemRequest (String id , String name , String description , BigDecimal price){
}
