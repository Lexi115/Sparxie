package io.lexi115.sparxie.shop.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopTransactionService {
    private final ShopTransactionRepository shopTransactionRepository;

    public ShopTransaction getById(final UUID transactionId) {
        return shopTransactionRepository.findById(transactionId).orElse(null);
    }

    public void commitTransaction(final ShopTransaction transaction) {
        shopTransactionRepository.save(transaction);
    }
}
