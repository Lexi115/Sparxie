package io.lexi115.sparxie.shop.shop.dto;

import io.lexi115.sparxie.shop.shop.ShopCurrency;
import io.lexi115.sparxie.shop.shop.item.ShopItem;
import io.lexi115.sparxie.shop.shop.item.dto.ShopItemDto;
import org.mapstruct.EnumMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopMapper {
    @EnumMapping(nameTransformationStrategy = "case", configuration = "lower")
    String toStringCurrency(ShopCurrency currency);

    ShopItemDto toDto(ShopItem item);

    PurchaseResponseDto toDto(PurchaseResponse response);
}
