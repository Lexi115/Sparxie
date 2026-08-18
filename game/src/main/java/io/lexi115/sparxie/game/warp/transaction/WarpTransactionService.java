package io.lexi115.sparxie.game.warp.transaction;

import io.lexi115.sparxie.game.messaging.OutboxEventService;
import io.lexi115.sparxie.game.warp.event.WarpPerformedEvent;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class WarpTransactionService {
    private final WarpTransactionRepository warpTransactionRepository;
    private final OutboxEventService outboxEventService;

    public WarpTransactionService(final WarpTransactionRepository warpTransactionRepository, final OutboxEventService outboxEventService) {
        this.warpTransactionRepository = warpTransactionRepository;
        this.outboxEventService = outboxEventService;
    }

    public WarpTransaction getOrCreateTransaction(final UUID transactionId, final UUID playerId) {
        var oldTransaction = warpTransactionRepository.findById(transactionId).orElse(null);
        if (oldTransaction != null) {
            return oldTransaction;
        }

        var newTransaction = new WarpTransaction(transactionId, playerId, Instant.now(), WarpTransactionStatus.PENDING);
        try {
            warpTransactionRepository.save(newTransaction);
            return newTransaction;
        } catch (Exception e) { // duplicate key
            return warpTransactionRepository.findById(transactionId)
                    .orElseThrow(() -> new IllegalStateException("Transaction should exist but wasn't found."));
        }
    }

    //@Transactional
    public void commitTransaction(final WarpTransaction transaction, final WarpPerformedEvent event) {
        transaction.setStatus(WarpTransactionStatus.COMPLETED);
        warpTransactionRepository.save(transaction);
        outboxEventService.scheduleEvent(event, "gacha-topic");
    }


}
