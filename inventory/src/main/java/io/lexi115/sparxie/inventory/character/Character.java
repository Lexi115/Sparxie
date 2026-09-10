package io.lexi115.sparxie.inventory.character;

import io.lexi115.sparxie.inventory.inventory.StarRarity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "characters")
@Getter
@Setter
@NoArgsConstructor
public final class Character {
    @Id
    private String id;

    private String name;

    private StarRarity rarity;
}
