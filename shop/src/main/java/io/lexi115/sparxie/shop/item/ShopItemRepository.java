package io.lexi115.sparxie.shop.item;

import io.lexi115.sparxie.shop.core.ShopItem;

import java.util.Optional;

public interface ShopItemRepository {
    Optional<ShopItem> findById(String id);
}
