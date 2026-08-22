package io.lexi115.sparxie.game.core;

import io.lexi115.sparxie.game.banner.BannerNotFoundException;
import io.lexi115.sparxie.game.banner.BannerService;
import io.lexi115.sparxie.game.economy.EconomyService;
import io.lexi115.sparxie.game.inventory.InventoryService;
import io.lexi115.sparxie.game.warp.WarpService;
import io.lexi115.sparxie.game.warp.dto.WarpRequest;
import io.lexi115.sparxie.game.warp.dto.WarpResultDto;
import io.lexi115.sparxie.game.warp.dto.WarpResultItemDto;
import io.lexi115.sparxie.game.warp.transaction.WarpTransactionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameService {

    private final BannerService bannerService;
    private final WarpService warpService;
    private final WarpTransactionService warpTransactionService;
    private final EconomyService economyService;
    private final InventoryService inventoryService;

    public GameService(
            final BannerService bannerService,
            final WarpService warpService,
            final WarpTransactionService warpTransactionService,
            final EconomyService economyService,
            final InventoryService inventoryService
    ) {
        this.bannerService = bannerService;
        this.warpService = warpService;
        this.warpTransactionService = warpTransactionService;
        this.economyService = economyService;
        this.inventoryService = inventoryService;
    }

    public WarpResultDto performWarp(final WarpRequest request) {
        var bannerDetails = bannerService.getDetailsById(request.bannerId());
        if (bannerDetails == null) {
            throw new BannerNotFoundException();
        }
        var transactionId = request.transactionId();
        var playerId = request.playerId();
        var transaction = warpTransactionService.getOrCreateTransaction(transactionId, playerId);
        if (transaction.isCompleted()) {
            return warpService.pull(request);
        }

        economyService.withdraw(transactionId, playerId, bannerDetails.currency(), bannerDetails.getCost(request.amount()));
        var warpResult = warpService.pull(request);
        var countedItems = countItems(warpResult.items());
        inventoryService.addItems(transactionId, playerId, countedItems);
        warpTransactionService.commitTransaction(transaction, countedItems);

        return warpResult;
    }

    private Map<String, Long> countItems(List<WarpResultItemDto> pulledItems) {
        var map = new HashMap<String, Long>();
        pulledItems.forEach(pulledItem -> {
            var itemId = pulledItem.itemId();
            map.put(itemId, map.getOrDefault(itemId, 0L) + 1);
        });
        return map;
    }
}
