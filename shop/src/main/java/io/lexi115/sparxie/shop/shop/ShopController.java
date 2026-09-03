package io.lexi115.sparxie.shop.shop;

import io.lexi115.sparxie.shop.shop.dto.PurchaseRequest;
import io.lexi115.sparxie.shop.shop.dto.PurchaseResponseDto;
import io.lexi115.sparxie.shop.shop.dto.ShopMapper;
import io.lexi115.sparxie.shop.shop.item.dto.ShopItemDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;
    private final ShopMapper shopMapper;

    @GetMapping("/items/{id}")
    public ShopItemDto getItemById(@PathVariable final String id) {
        var shopItem = shopService.getItemById(id);
        return shopMapper.toDto(shopItem);
    }

    @PostMapping("/items/purchase")
    public PurchaseResponseDto purchaseItem(@Valid @RequestBody final PurchaseRequest request) {
        var response = shopService.purchaseItem(
                request.transactionId(), request.playerId(), request.itemId(), request.amount());
        return shopMapper.toDto(response);
    }
}
