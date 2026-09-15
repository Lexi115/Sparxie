package io.lexi115.sparxie.game.warp.transactions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarpTransactionService {
    private final WarpTransactionRepository warpTransactionRepository;

    public WarpTransaction getById(final UUID transactionId) {
        return warpTransactionRepository.findById(transactionId).orElse(null);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public WarpTransaction create(final UUID transactionId, final UUID playerId) {
        var transaction = WarpTransaction.builder()
                .transactionId(transactionId)
                .playerId(playerId)
                .status(WarpTransactionStatus.PENDING)
                .build();
        warpTransactionRepository.save(transaction);
        return transaction;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void commit(final WarpTransaction transaction) {
        transaction.setStatus(WarpTransactionStatus.COMPLETED);
        warpTransactionRepository.save(transaction);
    }
}
