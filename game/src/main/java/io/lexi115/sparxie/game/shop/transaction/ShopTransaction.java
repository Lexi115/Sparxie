package io.lexi115.sparxie.game.shop.transaction;

import io.lexi115.sparxie.game.shop.ShopCurrency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class ShopTransaction {
    private UUID transactionId;
    private UUID playerId;
    private Instant createdAt;

    @Setter
    private ShopCurrency currency;

    @Setter
    private BigDecimal price = BigDecimal.ZERO;

    @Setter
    private Map<String, Long> items;

    @Setter
    private ShopTransactionStatus status;

    public ShopTransaction(
            final UUID transactionId,
            final UUID playerId,
            final Instant createdAt,
            final ShopTransactionStatus status
    ) {
        this.transactionId = transactionId;
        this.playerId = playerId;
        this.createdAt = createdAt;
        this.status = status;
    }

    public boolean isCompleted() {
        return this.status == ShopTransactionStatus.COMPLETED;
    }
}
