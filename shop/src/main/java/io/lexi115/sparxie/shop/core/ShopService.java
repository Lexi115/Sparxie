package io.lexi115.sparxie.shop.core;

import io.lexi115.sparxie.shop.concurrent.Lock;
import io.lexi115.sparxie.shop.item.ShopItem;
import io.lexi115.sparxie.shop.item.ShopItemService;
import io.lexi115.sparxie.shop.payment.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final Lock playerLock;
    private final ShopItemService shopItemService;
    private final PaymentGateway paymentGateway;

    public ShopItem getItemById(final String id) {
        return shopItemService.getById(id);
    }

    public void purchaseItem(final UUID transactionId, final UUID playerId, final String itemId, final Long amount) {
        var lockName = "shop_lock_" + playerId;
        if (!playerLock.acquire(lockName)) {
            throw new ShopLockedException("Please wait a bit before using the shop again!");
        }
        try {
            var shopItem = getItemById(itemId);
            if (shopItem.getCurrency() == ShopCurrency.MONEY) {
                paymentGateway.pay(transactionId, playerId, BigDecimal.valueOf(
                        shopItem.getCost()).multiply(BigDecimal.valueOf(amount))); // Fake payment
            }
        } finally {
            playerLock.release(lockName);
        }
    }
}
