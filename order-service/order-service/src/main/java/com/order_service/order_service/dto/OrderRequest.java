package com.order_service.order_service.dto;

import java.math.BigDecimal;

public record OrderRequest (Long id, String orderNumber, String skuCode, int price , Integer quantity) {
}
