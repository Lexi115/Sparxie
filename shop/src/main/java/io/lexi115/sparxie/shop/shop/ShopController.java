package io.lexi115.sparxie.shop.shop;

import io.lexi115.sparxie.shop.shop.dto.PurchaseRequest;
import io.lexi115.sparxie.shop.shop.dto.PurchaseResponseDto;
import io.lexi115.sparxie.shop.shop.dto.ShopMapper;
import io.lexi115.sparxie.shop.shop.items.dto.ShopItemDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;
    private final ShopMapper shopMapper;

    @GetMapping("/items/{itemId}")
    public ShopItemDto getItemById(@PathVariable final String itemId) {
        var shopItem = shopService.getItemById(itemId);
        return shopMapper.toDto(shopItem);
    }

    @PostMapping("/purchases")
    public PurchaseResponseDto purchaseItem(
            @Valid @RequestBody final PurchaseRequest request,
            @RequestHeader("X-User-Id") final UUID playerId
    ) {
        var response = shopService.purchaseItem(
                request.transactionId(), playerId, request.itemId(), request.amount());
        return shopMapper.toDto(response);
    }
}
