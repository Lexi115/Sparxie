package io.lexi115.sparxie.shop.shop.items.exceptions;

public class ShopItemNotFoundException extends RuntimeException {
    public ShopItemNotFoundException(final String itemId) {
        super("Shop item with ID '" + itemId + "' not found.");
    }
}
