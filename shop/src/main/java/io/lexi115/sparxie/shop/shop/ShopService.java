package io.lexi115.sparxie.shop.shop;

import io.lexi115.sparxie.shop.concurrent.Lock;
import io.lexi115.sparxie.shop.payments.PaymentGateway;
import io.lexi115.sparxie.shop.shop.dto.PurchaseResponse;
import io.lexi115.sparxie.shop.shop.exceptions.ShopLockedException;
import io.lexi115.sparxie.shop.shop.items.ShopItem;
import io.lexi115.sparxie.shop.shop.items.ShopItemService;
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

    public PurchaseResponse purchaseItem(final UUID transactionId, final UUID playerId, final String itemId, final Long amount) {
        var lockName = "shop_lock_" + playerId;
        if (!playerLock.acquire(lockName)) {
            throw new ShopLockedException("Please wait a bit before using the shop again!");
        }
        try {
            var shopItem = getItemById(itemId);
            var price = BigDecimal.valueOf(shopItem.getCost()).multiply(BigDecimal.valueOf(amount));
            var currency = shopItem.getCurrency();
            if (currency == ShopCurrency.MONEY) {
                paymentGateway.pay(transactionId, playerId, price); // Fake payment
            }
            return new PurchaseResponse(itemId, amount, currency, price);
        } finally {
            playerLock.release(lockName);
        }
    }
}
