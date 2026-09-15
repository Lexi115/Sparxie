package io.lexi115.sparxie.game.inventories.exception;

import lombok.Getter;

@Getter
public class NotEnoughItemsException extends RuntimeException {

    private final String itemId;
    private final Long possessedAmount;
    private final Long requiredAmount;

    public NotEnoughItemsException(final String itemId, final Long possessedAmount, final Long requiredAmount) {
        this("There aren't enough items with ID '" + itemId + "'.", itemId, possessedAmount, requiredAmount);
    }

    public NotEnoughItemsException(
            final String message,
            final String itemId,
            final Long possessedAmount,
            final Long requiredAmount
    ) {
        super(message);
        this.itemId = itemId;
        this.possessedAmount = possessedAmount;
        this.requiredAmount = requiredAmount;
    }
}
