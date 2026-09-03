package io.lexi115.sparxie.game.game;

import io.lexi115.sparxie.game.banner.BannerService;
import io.lexi115.sparxie.game.game.dto.*;
import io.lexi115.sparxie.game.inventory.InventoryService;
import io.lexi115.sparxie.game.shop.ShopService;
import io.lexi115.sparxie.game.warp.WarpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {
    private final BannerService bannerService;
    private final WarpService warpService;
    private final InventoryService inventoryService;
    private final ShopService shopService;

    @Transactional
    public WarpResponse performWarp(final UUID playerId, final WarpRequest request) {
        var bannerDetails = bannerService.getDetailsById(request.bannerId());
        var transactionId = request.transactionId();

        var transaction = warpService.startTransaction(transactionId, playerId);
        if (transaction.isCompleted()) {
            return transaction.getResult();
        }

        inventoryService.consumeItems(transactionId, playerId, Map.of(
                bannerDetails.currency(), bannerDetails.getCost(request.amount())));

        var response = warpService.performWarp(playerId, request);
        var groupedItems = groupItems(response.items());
        inventoryService.giveItems(transactionId, playerId, groupedItems);
        transaction.setResult(response);
        warpService.commitTransaction(transaction);
        return response;
    }

    private Map<String, Long> groupItems(final List<WarpItem> pulledItems) {
        var map = new HashMap<String, Long>();
        pulledItems.forEach(pulledItem -> {
            var itemId = pulledItem.itemId();
            map.put(itemId, map.getOrDefault(itemId, 0L) + 1);
        });
        return map;
    }

    @Transactional
    public PurchaseResponse performPurchase(final UUID playerId, final PurchaseRequest request) {
        var transactionId = request.transactionId();
        var itemId = request.itemId();
        var itemAmount = request.amount();
        var transaction = shopService.startTransaction(transactionId, playerId);
        if (transaction.isCompleted()) {
            return transaction.getResult();
        }

        var shopItem = shopService.getItemById(itemId);
        var response = shopService.purchaseItem(playerId, request);
        var currency = shopItem.currency();

        // If item was bought with money, skip item consumption inside player's inventory.
        if (!currency.equals("money")) {
            inventoryService.consumeItems(transactionId, playerId, Map.of(currency, shopItem.cost() * itemAmount));
        }

        transaction.setResult(response);
        inventoryService.giveItems(transactionId, playerId, Map.of(itemId, itemAmount));
        shopService.commitTransaction(transaction);
        return response;
    }
}
