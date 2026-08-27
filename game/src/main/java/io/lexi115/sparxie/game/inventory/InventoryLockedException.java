package io.lexi115.sparxie.game.inventory;

public class InventoryLockedException extends RuntimeException {
    public InventoryLockedException(final String message) {
        super(message);
    }
}
