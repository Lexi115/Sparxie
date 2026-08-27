package io.lexi115.sparxie.inventory.core;

public class InventoryLockedException extends RuntimeException {
    public InventoryLockedException(final String message) {
        super("Inventory locked: " + message);
    }
}
