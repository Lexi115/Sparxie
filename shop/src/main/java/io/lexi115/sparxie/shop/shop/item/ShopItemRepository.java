package io.lexi115.sparxie.shop.shop.item;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShopItemRepository extends MongoRepository<ShopItem, String> {
}
