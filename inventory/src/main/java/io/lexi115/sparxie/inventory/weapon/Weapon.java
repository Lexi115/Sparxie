package io.lexi115.sparxie.inventory.weapon;

import io.lexi115.sparxie.inventory.inventory.StarRarity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "weapons")
@Getter
@Setter
@NoArgsConstructor
public final class Weapon {
    @Id
    private String id;

    private String name;

    private StarRarity rarity;
}
