package io.lexi115.sparxie.game.inventory.exception;

public class InventoryLockedException extends RuntimeException {
    public InventoryLockedException(final String message) {
        super(message);
    }
}
