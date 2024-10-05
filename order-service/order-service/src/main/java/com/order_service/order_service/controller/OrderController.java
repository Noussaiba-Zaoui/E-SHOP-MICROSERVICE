package com.order_service.order_service.controller;


import com.order_service.order_service.dto.OrderRequest;
import com.order_service.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            orderService.placeOrder(orderRequest);
            return "Order Placed Successfully";
        } catch (Exception e) {
            // Log the exception (optional, you can use a logger)
            System.err.println("Error while placing order: " + e.getMessage());

            // Return a meaningful message to the client
            return "Error while placing order: " + e.getMessage();
        }
    }

}
