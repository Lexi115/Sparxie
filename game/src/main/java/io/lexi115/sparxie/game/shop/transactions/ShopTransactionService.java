package io.lexi115.sparxie.game.shop.transactions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopTransactionService {
    private final ShopTransactionRepository shopTransactionRepository;

    public ShopTransaction getById(final UUID transactionId, final UUID playerId) {
        return shopTransactionRepository.findById(transactionId).orElse(null);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ShopTransaction create(final UUID transactionId, final UUID playerId) {
        var transaction = ShopTransaction.builder()
                .transactionId(transactionId)
                .playerId(playerId)
                .createdAt(Instant.now())
                .status(ShopTransactionStatus.PENDING)
                .build();
        shopTransactionRepository.save(transaction);
        return transaction;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void commit(final ShopTransaction transaction) {
        transaction.setStatus(ShopTransactionStatus.COMPLETED);
        shopTransactionRepository.save(transaction);
    }
}
