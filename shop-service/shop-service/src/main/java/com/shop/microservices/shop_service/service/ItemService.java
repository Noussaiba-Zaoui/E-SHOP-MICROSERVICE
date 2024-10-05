package com.shop.microservices.shop_service.service;


import com.shop.microservices.shop_service.Model.Item;
import com.shop.microservices.shop_service.dto.ItemRequest;
import com.shop.microservices.shop_service.dto.ItemResponse;
import com.shop.microservices.shop_service.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ItemService {


    private final ItemRepository itemRepository;

    public ItemResponse createItem(ItemRequest itemRequest) {
        Item item = Item.builder()
                .id(itemRequest.id())
                .name(itemRequest.name())
                .description(itemRequest.description())
                .price(itemRequest.price())
                .build();
        itemRepository.save(item);
        return  new ItemResponse(item.getId(), item.getName(), item.getDescription(), item.getPrice());

    }

    public List<ItemResponse> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(item -> new ItemResponse(item.getId(), item.getName(), item.getDescription(), item.getPrice()))
                .toList();
    }
}
