package io.lexi115.sparxie.gacha.warp.transaction;

import io.lexi115.sparxie.gacha.warp.WarpResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarpTransactionService {
    private final WarpTransactionRepository warpTransactionRepository;

    public WarpTransaction getById(final UUID id) {
        return warpTransactionRepository.findById(id).orElse(null);
    }

    public WarpTransaction createTransaction(final UUID transactionId, final UUID playerId, final WarpResult warpResult) {
        return new WarpTransaction(transactionId, playerId, Instant.now(), warpResult);
    }

    public void saveTransaction(final WarpTransaction transaction) {
        warpTransactionRepository.save(transaction);
    }
}
