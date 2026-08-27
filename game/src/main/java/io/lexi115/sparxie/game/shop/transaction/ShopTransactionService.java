package io.lexi115.sparxie.game.shop.transaction;

import io.lexi115.sparxie.game.messaging.OutboxEventService;
import io.lexi115.sparxie.game.shop.event.PurchasePerformedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopTransactionService {
    private final ShopTransactionRepository shopTransactionRepository;
    private final OutboxEventService outboxEventService;

    // @Transactional
    public ShopTransaction getOrCreateTransaction(final UUID transactionId, final UUID playerId) {
        var oldTransaction = shopTransactionRepository.findById(transactionId).orElse(null);
        if (oldTransaction != null) {
            return oldTransaction;
        }

        var newTransaction = new ShopTransaction(transactionId, playerId, Instant.now(), ShopTransactionStatus.PENDING);
        try {
            shopTransactionRepository.save(newTransaction);
            return newTransaction;
        } catch (Exception e) { // duplicate key
            return shopTransactionRepository.findById(transactionId)
                    .orElseThrow(() -> new IllegalStateException("Transaction should exist but wasn't found."));
        }
    }

    // @Transactional
    public void commitTransaction(final ShopTransaction transaction) {
        transaction.setStatus(ShopTransactionStatus.COMPLETED);
        shopTransactionRepository.save(transaction);
        var event = new PurchasePerformedEvent(
                transaction.getTransactionId(),
                transaction.getPlayerId(),
                transaction.getCreatedAt(),
                transaction.getCurrency(),
                transaction.getPrice(),
                transaction.getItems()
        );
        outboxEventService.scheduleEvent(event, "shop-topic");
    }
}
