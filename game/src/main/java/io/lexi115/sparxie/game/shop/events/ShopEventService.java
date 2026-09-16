package io.lexi115.sparxie.game.shop.events;

import io.lexi115.sparxie.game.events.OutboxEventService;
import io.lexi115.sparxie.game.shop.transactions.ShopTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopEventService {
    private final OutboxEventService outboxEventService;

    @Value("${app.kafka.topic.shop}")
    private String shopTopicName;

    public void purchasePerformed(final ShopTransaction transaction) {
        var purchaseResponse = transaction.getResult();
        purchasePerformed(
                transaction.getTransactionId(),
                transaction.getPlayerId(),
                transaction.getCreatedAt(),
                purchaseResponse.currency(),
                purchaseResponse.price(),
                purchaseResponse.itemId(),
                purchaseResponse.amount()
        );
    }

    public void purchasePerformed(
            final UUID transactionId,
            final UUID playerId,
            final Instant createdAt,
            final String currency,
            final BigDecimal price,
            final String itemId,
            final Long amount
    ) {
        var event = new PurchasePerformedEvent(
                transactionId,
                playerId,
                createdAt,
                currency,
                price,
                itemId,
                amount
        );
        outboxEventService.scheduleEvent(event, playerId.toString(), shopTopicName);
    }
}
