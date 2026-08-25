package io.lexi115.sparxie.shop.item;

import io.lexi115.sparxie.shop.core.ShopItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopItemService {
    private final ShopItemRepository shopItemRepository;

    public ShopItem getById(final String id) {
        return shopItemRepository.findById(id).orElseThrow();
    }
}
