package io.lexi115.sparxie.game.shop;

import io.lexi115.sparxie.game.game.dto.PurchasableItem;
import io.lexi115.sparxie.game.game.dto.PurchaseRequest;
import io.lexi115.sparxie.game.game.dto.PurchaseResponse;
import io.lexi115.sparxie.game.shop.dto.ShopPurchasableItem;
import io.lexi115.sparxie.game.shop.dto.ShopPurchaseRequest;
import io.lexi115.sparxie.game.shop.dto.ShopPurchaseResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopMapper {
    PurchasableItem toClientItem(ShopPurchasableItem item);

    ShopPurchaseRequest toShopRequest(PurchaseRequest request);

    PurchaseResponse toClientResponse(ShopPurchaseResponse response);
}
