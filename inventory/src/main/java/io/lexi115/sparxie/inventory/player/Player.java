package io.lexi115.sparxie.inventory.player;

import io.lexi115.sparxie.inventory.inventory.Inventory;
import io.lexi115.sparxie.inventory.inventory.ItemType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
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
    private Inventory inventory = new Inventory();

    public Player(final UUID id) {
        this.id = id;
    }

    public void giveItem(final String itemId, final ItemType itemType, final Long amount) {
        inventory.addItem(itemId, itemType, amount);
    }

    public void consumeItem(final String itemId, final ItemType itemType, final Long amount) {
        inventory.removeItem(itemId, itemType, amount);
    }

    public Map<String, Long> getCharacters() {
        return inventory.getCharacters();
    }

    public Map<String, Long> getWeapons() {
        return inventory.getWeapons();
    }

    public Map<String, Long> getMaterials() {
        return inventory.getMaterials();
    }
}
