package io.lexi115.sparxie.game.warp.transaction;

import io.lexi115.sparxie.game.event.OutboxEventService;
import io.lexi115.sparxie.game.warp.event.WarpPerformedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarpTransactionService {
    private final WarpTransactionRepository warpTransactionRepository;
    private final OutboxEventService outboxEventService;

    // @Transactional
    public WarpTransaction getOrCreateTransaction(final UUID transactionId, final UUID playerId) {
        var oldTransaction = warpTransactionRepository.findById(transactionId).orElse(null);
        if (oldTransaction != null) {
            return oldTransaction;
        }

        var newTransaction = new WarpTransaction(
                transactionId, playerId, Instant.now(), WarpTransactionStatus.PENDING, new HashMap<>());
        try {
            warpTransactionRepository.save(newTransaction);
            return newTransaction;
        } catch (Exception e) { // duplicate key
            return warpTransactionRepository.findById(transactionId)
                    .orElseThrow(() -> new IllegalStateException("Transaction should exist but wasn't found."));
        }
    }

    //@Transactional
    public void commitTransaction(final WarpTransaction transaction) {
        transaction.setStatus(WarpTransactionStatus.COMPLETED);
        warpTransactionRepository.save(transaction);
        var event = new WarpPerformedEvent(
                transaction.getTransactionId(),
                transaction.getPlayerId(),
                transaction.getCreatedAt(),
                transaction.getItems()
        );
        outboxEventService.scheduleEvent(event, "warp-topic");
    }
}
