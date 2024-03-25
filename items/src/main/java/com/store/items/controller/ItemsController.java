package com.store.items.controller;

import com.store.items.dto.request.ItemRequest;
import com.store.items.dto.response.ItemResponse;
import com.store.items.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/items")
public class ItemsController {

    @Autowired
    ItemService itemService;

    @PostMapping
    public ItemResponse addItem(@RequestBody ItemRequest itemRequest) {
        return itemService.addItem(itemRequest);
    }

    @GetMapping
    public Page<ItemResponse> listItems(Pageable pageable) {
        return itemService.listItems(pageable);
    }

    @GetMapping("/{itemId}")
    public ItemResponse itemById(@PathVariable String itemId) {
        return itemService.itemById(itemId);
    }
}

