package com.store.items.service;

import com.store.items.dto.errors.ItemException;
import com.store.items.dto.request.ItemRequest;
import com.store.items.dto.response.ItemResponse;
import com.store.items.persistence.entity.Item;
import com.store.items.persistence.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public ItemResponse addItem(ItemRequest itemRequest) {
        Item savedItem = itemRepository.save(Item.fromItemRequest(itemRequest));

        return ItemResponse.fromItemResponse(savedItem);
    }

    public Page<ItemResponse> listItems(Pageable pageable) {
        return itemRepository.findAll(pageable).map(ItemResponse::fromItemResponse);
    }

    public ItemResponse itemById(String itemId) {
        return itemRepository.findById(itemId)
                .map(ItemResponse::fromItemResponse)
                .orElseThrow(() -> new ItemException("Item not found"));
    }
}
