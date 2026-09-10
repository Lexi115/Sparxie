package io.lexi115.sparxie.game.shop;

import io.lexi115.sparxie.game.game.dto.PurchasableItem;
import io.lexi115.sparxie.game.game.dto.PurchaseRequest;
import io.lexi115.sparxie.game.game.dto.PurchaseResponse;
import io.lexi115.sparxie.game.shop.dto.ShopMapper;
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
    private final ShopMapper shopMapper;

    public PurchasableItem getItemById(final String id) {
        var item = shopClient.getItemById(id);
        return shopMapper.toClientItem(item);
    }

    public ShopTransaction startTransaction(final UUID transactionId, final UUID playerId) {
        return shopTransactionService.getOrCreate(transactionId, playerId);
    }

    public void commitTransaction(final ShopTransaction transaction) {
        shopTransactionService.commit(transaction);
    }

    public PurchaseResponse purchaseItem(final UUID playerId, final PurchaseRequest request) {
        var shopRequest = shopMapper.toShopRequest(playerId, request);
        var shopResponse = shopClient.purchaseItem(shopRequest);
        return shopMapper.toClientResponse(shopResponse);
    }
}
