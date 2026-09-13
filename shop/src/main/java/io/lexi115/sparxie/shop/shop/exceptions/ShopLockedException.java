package io.lexi115.sparxie.shop.shop.exceptions;

public class ShopLockedException extends RuntimeException {
    public ShopLockedException(final String message) {
        super("Shop locked: " + message);
    }
}
