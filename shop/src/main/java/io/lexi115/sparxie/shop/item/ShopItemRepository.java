package io.lexi115.sparxie.shop.item;

import java.util.Optional;

public interface ShopItemRepository {
    Optional<ShopItem> findById(String id);
}
