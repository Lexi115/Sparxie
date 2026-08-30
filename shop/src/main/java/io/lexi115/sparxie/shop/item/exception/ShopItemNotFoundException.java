package io.lexi115.sparxie.shop.item.exception;

public class ShopItemNotFoundException extends RuntimeException {
    public ShopItemNotFoundException(final String itemId) {
        super("Shop item with ID '" + itemId + "' not found.");
    }
}
