package io.lexi115.sparxie.inventory.player;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlayerCharacter {
    private final String characterId;

    private Integer copies = 1;

    public void incrementCopies() {
        if (this.copies == 7) {
            throw new NumberOfCopiesExceededException(characterId);
        }
        this.copies++;
    }
}
