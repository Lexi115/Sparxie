package io.lexi115.sparxie.inventory.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@ToString
public class PlayerWeapon {
    private final UUID id = UUID.randomUUID();
    private final String itemId;
}
