package io.lexi115.sparxie.shop.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopItemService {
    private final ShopItemRepository shopItemRepository;

    public ShopItem getById(final String id) {
        return shopItemRepository.findById(id)
                .orElseThrow(() -> new ShopItemNotFoundException(id));
    }
}
