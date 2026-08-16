package io.lexi115.sparxie.gacha.warp.transaction;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WarpTransactionService {
    private final WarpTransactionRepository warpTransactionRepository;

    public WarpTransactionService(final WarpTransactionRepository warpTransactionRepository) {
        this.warpTransactionRepository = warpTransactionRepository;
    }

    public WarpTransaction getById(final UUID id) {
        return warpTransactionRepository.findById(id).orElse(null);
    }

    public void saveTransaction(final WarpTransaction transaction) {
        warpTransactionRepository.save(transaction);
    }
}
