package io.lexi115.sparxie.game.core;

import io.lexi115.sparxie.game.banner.BannerNotFoundException;
import io.lexi115.sparxie.game.banner.BannerService;
import io.lexi115.sparxie.game.economy.EconomyService;
import io.lexi115.sparxie.game.inventory.InventoryService;
import io.lexi115.sparxie.game.warp.WarpService;
import io.lexi115.sparxie.game.warp.dto.WarpRequest;
import io.lexi115.sparxie.game.warp.dto.WarpResultDto;
import io.lexi115.sparxie.game.warp.dto.WarpResultItemDto;
import io.lexi115.sparxie.game.warp.event.WarpPerformedEvent;
import io.lexi115.sparxie.game.warp.transaction.WarpTransactionService;
import org.springframework.stereotype.Service;

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
        var pulledItemIds = warpResult.items().stream().map(WarpResultItemDto::itemId).toList();
        inventoryService.addItems(transactionId, playerId, pulledItemIds);

        var event = new WarpPerformedEvent(transactionId, playerId, transaction.getCreatedAt(), pulledItemIds);
        warpTransactionService.commitTransaction(transaction, event);

        return warpResult;
    }
}
