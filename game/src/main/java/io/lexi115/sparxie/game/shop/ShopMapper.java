package io.lexi115.sparxie.game.shop;

import io.lexi115.sparxie.game.shop.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopMapper {
    PurchasableItem toClientItem(ShopPurchasableItem item);

    ShopPurchaseRequest toShopRequest(PurchaseRequest request);

    PurchaseResponse toClientResponse(ShopPurchaseResponse response);
}
