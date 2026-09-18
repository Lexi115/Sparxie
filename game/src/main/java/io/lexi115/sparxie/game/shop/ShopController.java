package io.lexi115.sparxie.game.shop;

import io.lexi115.sparxie.game.shop.dto.PurchasableItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/items/{itemId}")
    public PurchasableItem getItemById(@PathVariable final String itemId) {
        return shopService.getItemById(itemId);
    }
}
