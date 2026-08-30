package io.lexi115.sparxie.shop.shop.exception;

public class ShopLockedException extends RuntimeException {
    public ShopLockedException(final String message) {
        super("Shop locked: " + message);
    }
}
