package io.lexi115.sparxie.logger.shop;

import io.lexi115.sparxie.logger.shop.dto.PurchaseHistory;
import io.lexi115.sparxie.logger.shop.dto.PurchaseHistoryEntry;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ShopMapper {
    default PurchaseHistory toHistory(List<PurchasePerformed> original, UUID playerId) {
        return PurchaseHistory.builder()
                .playerId(playerId)
                .entries(original.stream().map(this::toHistoryEntry).toList())
                .build();
    }

    PurchaseHistoryEntry toHistoryEntry(PurchasePerformed original);
}
