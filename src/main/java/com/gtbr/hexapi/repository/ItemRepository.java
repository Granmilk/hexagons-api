package com.gtbr.hexapi.repository;

import com.gtbr.hexapi.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
