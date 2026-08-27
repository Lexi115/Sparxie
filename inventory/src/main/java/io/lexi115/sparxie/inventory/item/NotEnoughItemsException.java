package io.lexi115.sparxie.inventory.item;

import lombok.Getter;

@Getter
public class NotEnoughItemsException extends RuntimeException {

    private final String itemId;
    private final Long possessedAmount;
    private final Long requiredAmount;

    public NotEnoughItemsException(final String itemId, final Long possessedAmount, final Long requiredAmount) {
        super("There aren't enough items with ID '" + itemId + "'.");
        this.itemId = itemId;
        this.possessedAmount = possessedAmount;
        this.requiredAmount = requiredAmount;
    }
}
