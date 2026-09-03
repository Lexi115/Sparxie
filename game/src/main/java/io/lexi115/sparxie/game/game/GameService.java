package io.lexi115.sparxie.game.game;

import io.lexi115.sparxie.game.banner.BannerService;
import io.lexi115.sparxie.game.inventory.InventoryService;
import io.lexi115.sparxie.game.shop.ShopCurrency;
import io.lexi115.sparxie.game.shop.ShopService;
import io.lexi115.sparxie.game.shop.dto.PurchaseRequest;
import io.lexi115.sparxie.game.shop.dto.PurchaseResponse;
import io.lexi115.sparxie.game.warp.WarpService;
import io.lexi115.sparxie.game.warp.dto.WarpRequest;
import io.lexi115.sparxie.game.warp.dto.WarpResultDto;
import io.lexi115.sparxie.game.warp.dto.WarpResultItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GameService {
    private final BannerService bannerService;
    private final WarpService warpService;
    private final InventoryService inventoryService;
    private final ShopService shopService;

    // todo check player existence
    @Transactional
    public WarpResultDto performWarp(final WarpRequest request) {
        var bannerDetails = bannerService.getDetailsById(request.bannerId());
        var transactionId = request.transactionId();
        var playerId = request.playerId();

        var transaction = warpService.startTransaction(transactionId, playerId);
        if (transaction.isCompleted()) {
            return transaction.getResult();
        }

        inventoryService.consumeItems(transactionId, playerId, Map.of(
                bannerDetails.currency(), bannerDetails.getCost(request.amount())));

        var warpResult = warpService.performWarp(request);
        var groupedItems = groupItems(warpResult.items());
        inventoryService.giveItems(transactionId, playerId, groupedItems);
        transaction.setResult(warpResult);
        warpService.commitTransaction(transaction);
        return warpResult;
    }

    private Map<String, Long> groupItems(final List<WarpResultItemDto> pulledItems) {
        var map = new HashMap<String, Long>();
        pulledItems.forEach(pulledItem -> {
            var itemId = pulledItem.itemId();
            map.put(itemId, map.getOrDefault(itemId, 0L) + 1);
        });
        return map;
    }

    // todo check player existence
    @Transactional
    public PurchaseResponse performPurchase(final PurchaseRequest request) {
        var transactionId = request.transactionId();
        var playerId = request.playerId();
        var itemId = request.itemId();
        var itemAmount = request.amount();
        var transaction = shopService.startTransaction(transactionId, playerId);
        if (transaction.isCompleted()) {
            return transaction.getResult();
        }

        var shopItem = shopService.getItemById(itemId);
        var shopItemCurrency = shopItem.currency();
        var shopItemCost = shopItem.cost() * itemAmount;
        var purchaseResponse = shopService.purchaseItem(transactionId, playerId, itemId, itemAmount);
        if (shopItemCurrency != ShopCurrency.MONEY) {
            inventoryService.consumeItems(transactionId, playerId, Map.of(
                    shopItemCurrency.name(), shopItemCost)
            );
        }
        transaction.setResult(purchaseResponse);
        inventoryService.giveItems(transactionId, playerId, Map.of(itemId, itemAmount));
        shopService.commitTransaction(transaction);
        return purchaseResponse;
    }
}
