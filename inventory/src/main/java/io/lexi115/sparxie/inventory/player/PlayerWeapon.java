package io.lexi115.sparxie.inventory.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlayerWeapon {
    private final String weaponId;

    private Integer copies = 1;

    public void incrementCopies() {
        this.copies++;
    }
}
