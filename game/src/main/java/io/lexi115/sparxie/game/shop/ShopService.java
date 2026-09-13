package io.lexi115.sparxie.game.shop;

import io.lexi115.sparxie.game.game.dto.PurchasableItem;
import io.lexi115.sparxie.game.game.dto.PurchaseRequest;
import io.lexi115.sparxie.game.game.dto.PurchaseResponse;
import io.lexi115.sparxie.game.shop.transactions.ShopTransaction;
import io.lexi115.sparxie.game.shop.transactions.ShopTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopClient shopClient;
    private final ShopTransactionService shopTransactionService;
    private final ShopMapper shopMapper;

    public PurchasableItem getItemById(final String itemId) {
        var item = shopClient.getItemById(itemId);
        return shopMapper.toClientItem(item);
    }

    public ShopTransaction startTransaction(final UUID transactionId, final UUID playerId) {
        return shopTransactionService.getOrCreate(transactionId, playerId);
    }

    public void commitTransaction(final ShopTransaction transaction) {
        shopTransactionService.commit(transaction);
    }

    public PurchaseResponse purchaseItem(final PurchaseRequest request, final UUID playerId) {
        var shopRequest = shopMapper.toShopRequest(request);
        var shopResponse = shopClient.purchaseItem(shopRequest, playerId);
        return shopMapper.toClientResponse(shopResponse);
    }
}
