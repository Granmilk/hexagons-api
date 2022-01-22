package com.gtbr.hexapi.service;

import com.gtbr.hexapi.entity.Item;
import com.gtbr.hexapi.entity.UserItem;
import com.gtbr.hexapi.entity.UserProfile;
import com.gtbr.hexapi.enums.Operator;
import com.gtbr.hexapi.exception.ObjectNotFoundException;
import com.gtbr.hexapi.record.UserItemRecord;
import com.gtbr.hexapi.repository.ItemRepository;
import com.gtbr.hexapi.repository.UserItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserItemRepository userItemRepository;
    private final UserProfileService userProfileService;

    public List<Item> findItemList() {
        return (List<Item>) itemRepository.findAll();
    }

    public UserItemRecord findItemsByUser(UUID userId) {
        return new UserItemRecord(userId, userItemRepository.findByUserId(userId).stream()
                .map(UserItem::getItem).toList());
    }

    public UserItemRecord buy(UUID userId, Long itemId) {
        UserProfile user = userProfileService.findUserById(userId);
        Item item = itemRepository.findById(itemId).orElseThrow(() -> {
            throw new ObjectNotFoundException("Item not found", HttpStatus.NOT_FOUND);
        });

        if (user.getCoin() >= item.getValue()) {
            userProfileService.updateCoins(userId, Operator.MINUS, item.getValue());
            userItemRepository.save(UserItem.builder().item(item).userProfile(user).registeredAt(LocalDateTime.now()).build());

            return findItemsByUser(userId);
        }

        throw new ObjectNotFoundException("Not enough coins", HttpStatus.FORBIDDEN);
    }

}
