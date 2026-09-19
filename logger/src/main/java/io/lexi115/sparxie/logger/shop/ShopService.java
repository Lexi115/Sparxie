package io.lexi115.sparxie.logger.shop;

import io.lexi115.sparxie.logger.shop.dto.PurchaseHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final PurchasePerformedRepository purchasePerformedRepository;
    private final ShopMapper shopMapper;

    public PurchaseHistory getHistory(final UUID playerId, final Pageable pageable) {
        var responseList = purchasePerformedRepository.findAll(playerId, pageable);
        return shopMapper.toHistory(responseList, playerId);
    }

    public void createPurchasePerformed(
            final UUID transactionId,
            final UUID playerId,
            final Instant createdAt,
            final String currency,
            final BigDecimal price,
            final String itemId,
            final Long amount
    ) {
        var purchasePerformed = PurchasePerformed.builder()
                .transactionId(transactionId)
                .playerId(playerId)
                .createdAt(createdAt)
                .currency(currency)
                .price(price)
                .itemId(itemId)
                .amount(amount)
                .build();
        purchasePerformedRepository.save(purchasePerformed);
    }
}
