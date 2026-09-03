package io.lexi115.sparxie.game.shop.dto;

import io.lexi115.sparxie.game.game.dto.PurchasableItem;
import io.lexi115.sparxie.game.game.dto.PurchaseRequest;
import io.lexi115.sparxie.game.game.dto.PurchaseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ShopMapper {
    PurchasableItem toClientItem(ShopPurchasableItem item);

    @Mapping(target = "playerId", source = "playerId")
    ShopPurchaseRequest toShopRequest(UUID playerId, PurchaseRequest request);

    PurchaseResponse toClientResponse(ShopPurchaseResponse response);
}
