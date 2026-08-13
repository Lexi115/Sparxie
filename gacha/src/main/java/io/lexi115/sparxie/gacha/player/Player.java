package io.lexi115.sparxie.gacha.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Player {
    private UUID id;
    private PlayerPity pity;
}
