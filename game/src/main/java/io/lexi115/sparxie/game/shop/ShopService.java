package io.lexi115.sparxie.game.shop;

import io.lexi115.sparxie.game.shop.dto.PurchaseRequest;
import io.lexi115.sparxie.game.shop.dto.PurchaseResponse;
import io.lexi115.sparxie.game.shop.dto.ShopItemDto;
import io.lexi115.sparxie.game.shop.transaction.ShopTransaction;
import io.lexi115.sparxie.game.shop.transaction.ShopTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopClient shopClient;
    private final ShopTransactionService shopTransactionService;

    public ShopItemDto getItemById(final String id) {
        return shopClient.getItemById(id);
    }

    public ShopTransaction startTransaction(final UUID transactionId, final UUID playerId) {
        return shopTransactionService.getOrCreate(transactionId, playerId);
    }

    public void commitTransaction(final ShopTransaction transaction) {
        shopTransactionService.commit(transaction);
    }

    public PurchaseResponse purchaseItem(final UUID transactionId, final UUID playerId, final String itemId, final Long amount) {
        var request = new PurchaseRequest(transactionId, playerId, itemId, amount);
        return shopClient.purchaseItem(request);
    }
}
