package io.lexi115.sparxie.gacha.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Player {
    private UUID id;
    private PlayerPity pity = new PlayerPity();
}
