package io.lexi115.sparxie.inventory.inventories.exceptions;

public class InventoryLockedException extends RuntimeException {
    public InventoryLockedException(final String message) {
        super("Inventory locked: " + message);
    }
}
