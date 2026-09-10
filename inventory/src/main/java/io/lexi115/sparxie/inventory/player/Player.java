package io.lexi115.sparxie.inventory.player;

import io.lexi115.sparxie.inventory.inventory.ItemType;
import io.lexi115.sparxie.inventory.inventory.exception.NotEnoughItemsException;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Document(collection = "players")
@Getter
@Setter
@NoArgsConstructor
public class Player {
    @Id
    private UUID id;

    @CreatedDate
    @Field("created_at")
    private Instant createdAt;

    @NotNull
    private Map<String, Long> characters = new HashMap<>();

    @NotNull
    private Map<String, Long> weapons = new HashMap<>();

    @NotNull
    private Map<String, Long> materials = new HashMap<>();

    public Player(final UUID id) {
        this.id = id;
    }

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
        var possessedAmount = itemMap.getOrDefault(itemId, 0L);
        var newQuantity = possessedAmount - amount;
        if (newQuantity < 0) {
            throw new NotEnoughItemsException(itemId, possessedAmount, Math.abs(newQuantity));
        }
        itemMap.put(itemId, newQuantity);
    }
}
