package io.lexi115.sparxie.gacha.players;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "players")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    private UUID id;

    private Pity pity;

    public Player(final UUID id) {
        this.id = id;
        this.pity = new Pity();
    }
}

