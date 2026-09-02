package io.lexi115.sparxie.game.shop.transaction;

import io.lexi115.sparxie.game.event.OutboxEventService;
import io.lexi115.sparxie.game.shop.event.PurchasePerformedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopTransactionService {
    private final ShopTransactionRepository shopTransactionRepository;
    private final OutboxEventService outboxEventService;

    @Value("${app.kafka.topic.shop}")
    private String shopTopic;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ShopTransaction getOrCreate(final UUID transactionId, final UUID playerId) {
        var oldTransaction = shopTransactionRepository.findById(transactionId).orElse(null);
        if (oldTransaction != null) {
            return oldTransaction;
        }

        var newTransaction = ShopTransaction.builder()
                .transactionId(transactionId)
                .playerId(playerId)
                .status(ShopTransactionStatus.PENDING)
                .build();
        try {
            shopTransactionRepository.save(newTransaction);
            return newTransaction;
        } catch (Exception e) { // duplicate key
            return shopTransactionRepository.findById(transactionId)
                    .orElseThrow(() -> new IllegalStateException("Transaction should exist but wasn't found."));
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void commit(final ShopTransaction transaction) {
        transaction.setStatus(ShopTransactionStatus.COMPLETED);
        shopTransactionRepository.save(transaction);
        var purchaseResponse = transaction.getResponse();
        var event = new PurchasePerformedEvent(
                transaction.getTransactionId(),
                transaction.getPlayerId(),
                transaction.getCreatedAt(),
                purchaseResponse.currency(),
                purchaseResponse.price(),
                purchaseResponse.itemId(),
                purchaseResponse.amount()
        );
        outboxEventService.scheduleEvent(event, transaction.getPlayerId().toString(), shopTopic);
    }
}
