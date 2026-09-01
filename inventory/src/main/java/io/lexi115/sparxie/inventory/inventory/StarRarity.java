package io.lexi115.sparxie.inventory.inventory;

import lombok.Getter;

@Getter
public enum StarRarity {
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final Integer value;

    StarRarity(final Integer value) {
        this.value = value;
    }
}
