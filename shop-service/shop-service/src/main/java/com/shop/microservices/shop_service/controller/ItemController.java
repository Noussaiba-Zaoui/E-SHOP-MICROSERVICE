package com.shop.microservices.shop_service.controller;


import com.shop.microservices.shop_service.Model.Item;
import com.shop.microservices.shop_service.dto.ItemRequest;
import com.shop.microservices.shop_service.dto.ItemResponse;
import com.shop.microservices.shop_service.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shop")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse createItem(@RequestBody ItemRequest itemRequest){
        return itemService.createItem(itemRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ItemResponse> getAllItems(){
        return itemService.getAllItems();
    }
}
