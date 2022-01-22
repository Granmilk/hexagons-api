package com.gtbr.hexapi.record;

import com.gtbr.hexapi.entity.Item;

import java.util.List;
import java.util.UUID;

public record UserItemRecord(UUID userId, List<Item> items) {
}
