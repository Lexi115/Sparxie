package io.lexi115.sparxie.shop.core;

import io.lexi115.sparxie.shop.cache.Lock;
import io.lexi115.sparxie.shop.exchange.ExchangeRate;
import io.lexi115.sparxie.shop.game.GameCurrency;
import io.lexi115.sparxie.shop.inventory.InventoryService;
import io.lexi115.sparxie.shop.transaction.ShopTransaction;
import io.lexi115.sparxie.shop.transaction.ShopTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final InventoryService inventoryService;
    private final ShopTransactionService shopTransactionService;
    private final Lock playerLock;
    private final ExchangeRate exchangeRate;

    public void buyItem(final UUID transactionId, final UUID playerId, final String itemId, final Long amount, final BuyMethod buyMethod) {
        var lockName = "shop_lock_" + playerId;
        if (!playerLock.acquire(lockName)) {
            throw new RuntimeException("lock");
        }
        try {
            var cachedTransaction = shopTransactionService.getById(transactionId);
            if (cachedTransaction != null) {
                return;
            }

            if (buyMethod == BuyMethod.FAKE_MONEY) {
                // fake
            } else if (buyMethod == BuyMethod.JADES) {
                var costInJades = exchangeRate.getCostInJades(itemId);
                if (costInJades == null) {
                    throw new RuntimeException("Cannot buy this item: " + itemId);
                }
                inventoryService.consumeItems(transactionId, playerId,
                        Map.of(GameCurrency.STELLAR_JADE.name(), costInJades));
            }

            var itemsToGive = Map.of(itemId, amount);
            inventoryService.giveItems(transactionId, playerId, itemsToGive);
            var transaction = new ShopTransaction(transactionId, playerId, Instant.now(), itemsToGive);
            shopTransactionService.commitTransaction(transaction);
        } finally {
            playerLock.release(lockName);
        }
    }
}
