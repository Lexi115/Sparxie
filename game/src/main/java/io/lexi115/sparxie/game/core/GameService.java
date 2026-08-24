package io.lexi115.sparxie.game.core;

import io.lexi115.sparxie.game.banner.BannerService;
import io.lexi115.sparxie.game.inventory.InventoryService;
import io.lexi115.sparxie.game.util.UuidHelper;
import io.lexi115.sparxie.game.warp.WarpService;
import io.lexi115.sparxie.game.warp.dto.WarpRequest;
import io.lexi115.sparxie.game.warp.dto.WarpResultDto;
import io.lexi115.sparxie.game.warp.dto.WarpResultItemDto;
import io.lexi115.sparxie.game.warp.transaction.WarpTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GameService {

    private final BannerService bannerService;
    private final WarpService warpService;
    private final WarpTransactionService warpTransactionService;
    private final InventoryService inventoryService;
    private final UuidHelper uuidHelper;

    public WarpResultDto performWarp(final WarpRequest request) {
        var bannerDetails = bannerService.getDetailsById(request.bannerId());
        var transactionId = request.transactionId();
        var playerId = request.playerId();
        var transaction = warpTransactionService.getOrCreateTransaction(transactionId, playerId);

        // Check whether this transaction has been already completed (returning previously calculated warp result).
        if (transaction.isCompleted()) {
            return warpService.pull(request);
        }

        // Deduct tickets (if not done already)
        var consumeItemUuid = uuidHelper.generateNameUuid(transactionId + "_consume");
        inventoryService.consumeItems(consumeItemUuid, playerId, Map.of(
                bannerDetails.currency(), bannerDetails.getCost(request.amount())));

        // Pull and give items (if not done already)
        var warpResult = warpService.pull(request);
        var countedItems = countItems(warpResult.items());
        var giveItemUuid = uuidHelper.generateNameUuid(transactionId + "_give");
        inventoryService.giveItems(giveItemUuid, playerId, countedItems);
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
