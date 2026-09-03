package io.lexi115.sparxie.shop.shop;

import io.lexi115.sparxie.shop.shop.dto.PurchaseRequest;
import io.lexi115.sparxie.shop.shop.dto.PurchaseResponse;
import io.lexi115.sparxie.shop.shop.item.dto.ShopItemDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/items/{id}")
    public ShopItemDto getItemById(@PathVariable final String id) {
        var shopItem = shopService.getItemById(id);
        return new ShopItemDto(shopItem.getItemId(), shopItem.getCurrency().name(), shopItem.getCost());
    }

    @PostMapping("/items/purchase")
    public PurchaseResponse purchaseItem(@Valid @RequestBody final PurchaseRequest request) {
        return shopService.purchaseItem(request.transactionId(), request.playerId(), request.itemId(), request.amount());
    }
}
