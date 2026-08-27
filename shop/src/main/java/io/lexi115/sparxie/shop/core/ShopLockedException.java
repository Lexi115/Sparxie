package io.lexi115.sparxie.shop.core;

public class ShopLockedException extends RuntimeException {
    public ShopLockedException(final String message) {
        super("Shop locked: " + message);
    }
}
