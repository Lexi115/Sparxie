package io.lexi115.sparxie.game.game;

import io.lexi115.sparxie.game.banners.BannerService;
import io.lexi115.sparxie.game.game.dto.*;
import io.lexi115.sparxie.game.inventories.InventoryService;
import io.lexi115.sparxie.game.inventories.exception.NotEnoughItemsException;
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
    public WarpResponse performWarp(final WarpRequest request, final UUID playerId) {
        var transactionId = request.transactionId();
        var transaction = warpService.getTransaction(transactionId, playerId);
        if (transaction != null && transaction.isCompleted()) {
            return transaction.getResult();
        }

        var bannerDetails = bannerService.getDetailsById(request.bannerId());
        var currency = bannerDetails.currency();
        var cost = bannerDetails.getCost(request.amount());
        var possessedCurrencyAmount = inventoryService.getMaterialAmount(playerId, currency);

        // Check if player has enough balance first.
        if (possessedCurrencyAmount < cost) {
            throw new NotEnoughItemsException(currency, possessedCurrencyAmount, cost);
        }

        if (transaction == null) {
            transaction = warpService.createTransaction(transactionId, playerId);
        }
        inventoryService.consumeItem(transactionId, playerId, currency, cost);

        var response = warpService.performWarp(request, playerId);
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

    // todo refactor logic
    @Transactional
    public PurchaseResponse performPurchase(final PurchaseRequest request, final UUID playerId) {
        var transactionId = request.transactionId();
        var transaction = shopService.getTransaction(transactionId, playerId);
        if (transaction != null && transaction.isCompleted()) {
            return transaction.getResult();
        }

        var itemId = request.itemId();
        var itemAmount = request.amount();
        var shopItem = shopService.getItemById(itemId);
        var currency = shopItem.currency();
        var cost = shopItem.cost() * itemAmount;

        if (!currency.equals("money")) {
            var possessedCurrencyAmount = inventoryService.getMaterialAmount(playerId, currency);
            if (possessedCurrencyAmount < cost) {
                throw new NotEnoughItemsException(currency, possessedCurrencyAmount, cost);
            }
        }

        if (transaction == null) {
            transaction = shopService.createTransaction(transactionId, playerId);
        }
        var response = shopService.purchaseItem(request, playerId);

        // If item was bought with money, skip item consumption inside player's inventory.
        if (!currency.equals("money")) {
            inventoryService.consumeItem(transactionId, playerId, currency, cost);
        }

        transaction.setResult(response);
        inventoryService.giveItem(transactionId, playerId, itemId, itemAmount);
        shopService.commitTransaction(transaction);
        return response;
    }
}
