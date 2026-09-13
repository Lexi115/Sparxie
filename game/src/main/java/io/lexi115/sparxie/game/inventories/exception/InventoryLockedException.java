package io.lexi115.sparxie.game.inventories.exception;

public class InventoryLockedException extends RuntimeException {
    public InventoryLockedException(final String message) {
        super(message);
    }
}
