package io.lexi115.sparxie.inventory.player;

import io.lexi115.sparxie.inventory.core.ItemType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class Player {
    private final UUID id;
    private final Map<String, Long> characters = new HashMap<>();
    private final Map<String, Long> weapons = new HashMap<>();
    private final Map<String, Long> materials = new HashMap<>();

    public void giveItem(final String itemId, final ItemType itemType, final Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        switch (itemType) {
            case CHARACTER -> characters.merge(itemId, amount, Long::sum);
            case WEAPON -> weapons.merge(itemId, amount, Long::sum);
            case MATERIAL -> materials.merge(itemId, amount, Long::sum);
        }
    }

    public void consumeItem(final String itemId, final ItemType itemType, final Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        var itemMap = switch (itemType) {
            case CHARACTER -> characters;
            case WEAPON -> weapons;
            case MATERIAL -> materials;
        };
        var newQuantity = itemMap.getOrDefault(itemId, 0L) - amount;
        if (newQuantity < 0) {
            throw new NotEnoughItemsException(itemId);
        }
        itemMap.put(itemId, newQuantity);
    }
}
