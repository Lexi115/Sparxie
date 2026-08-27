package io.lexi115.sparxie.shop.item;

import io.lexi115.sparxie.shop.core.ShopCurrency;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ShopItemRepositoryImpl implements ShopItemRepository {

    private final Map<String, ShopItem> itemMap = new HashMap<>(Map.of(
            "oneiric_shard", new ShopItem("oneiric_shard", ShopCurrency.MONEY, 1L),
            "stellar_jade", new ShopItem("stellar_jade", ShopCurrency.ONEIRIC_SHARD, 1L),
            "standard_ticket", new ShopItem("standard_ticket", ShopCurrency.STELLAR_JADE, 160L),
            "limited_ticket", new ShopItem("limited_ticket", ShopCurrency.STELLAR_JADE, 160L)
    ));

    @Override
    public Optional<ShopItem> findById(String id) {
        return Optional.ofNullable(itemMap.get(id));
    }
}
