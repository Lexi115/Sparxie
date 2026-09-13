package io.lexi115.sparxie.inventory.inventories;

import io.lexi115.sparxie.inventory.inventories.exceptions.NotEnoughItemsException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class Inventory {
    private Map<String, Long> characters = new HashMap<>();
    private Map<String, Long> weapons = new HashMap<>();
    private Map<String, Long> materials = new HashMap<>();

    public void addItem(final String itemId, final ItemType itemType, final Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        switch (itemType) {
            case CHARACTER -> characters.merge(itemId, amount, Long::sum);
            case WEAPON -> weapons.merge(itemId, amount, Long::sum);
            case MATERIAL -> materials.merge(itemId, amount, Long::sum);
        }
    }

    public void removeItem(final String itemId, final ItemType itemType, final Long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        var itemMap = switch (itemType) {
            case CHARACTER -> characters;
            case WEAPON -> weapons;
            case MATERIAL -> materials;
        };
        var possessedAmount = itemMap.getOrDefault(itemId, 0L);
        var newQuantity = possessedAmount - amount;
        if (newQuantity < 0) {
            throw new NotEnoughItemsException(itemId, possessedAmount, Math.abs(newQuantity));
        }
        itemMap.put(itemId, newQuantity);
    }
}
