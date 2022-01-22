package com.gtbr.hexapi.controller;

import com.gtbr.hexapi.entity.Item;
import com.gtbr.hexapi.entity.UserItem;
import com.gtbr.hexapi.record.UserItemHttp;
import com.gtbr.hexapi.record.UserItemRecord;
import com.gtbr.hexapi.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> findAll(){
        return ResponseEntity.ok(itemService.findItemList());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserItemRecord> findByUserId(@PathVariable UUID userId){
        return ResponseEntity.ok(itemService.findItemsByUser(userId));
    }

    @PostMapping
    public ResponseEntity<UserItemRecord> createUserId(@RequestBody UserItemHttp userItem){
        return ResponseEntity.ok(itemService.buy(userItem.userId(), userItem.itemId()));
    }

}
