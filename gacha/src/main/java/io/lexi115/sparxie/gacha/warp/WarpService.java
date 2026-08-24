package io.lexi115.sparxie.gacha.warp;

import io.lexi115.sparxie.gacha.banner.BannerService;
import io.lexi115.sparxie.gacha.cache.Lock;
import io.lexi115.sparxie.gacha.player.PlayerService;
import io.lexi115.sparxie.gacha.warp.dto.WarpRequest;
import io.lexi115.sparxie.gacha.warp.transaction.WarpTransaction;
import io.lexi115.sparxie.gacha.warp.transaction.WarpTransactionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;

@Service
public class WarpService {

    private final BannerService bannerService;
    private final PlayerService playerService;
    private final WarpTransactionService warpTransactionService;
    private final Lock warpLock;

    public WarpService(BannerService bannerService, PlayerService playerService, WarpTransactionService warpTransactionService, Lock warpLock) {
        this.bannerService = bannerService;
        this.playerService = playerService;
        this.warpTransactionService = warpTransactionService;
        this.warpLock = warpLock;
    }

    // @Transactional
    public WarpResult pull(final WarpRequest request) {
        var playerId = request.playerId();
        var lockName = "warp_lock_" + playerId;
        if (!warpLock.acquire(lockName)) {
            throw new WarpException("Please wait before making another warp!");
        }

        try {
            var transactionUuid = request.transactionId();
            var cachedTransaction = warpTransactionService.getById(transactionUuid);
            if (cachedTransaction != null) {
                return cachedTransaction.result();
            }

            var player = playerService.getById(playerId);
            var banner = bannerService.getById(request.bannerId());

            var pullAmount = request.amount();
            if (pullAmount <= 0) {
                throw new IllegalArgumentException("Amount of pulls cannot be 0 or less");
            }

            var pulledItems = new ArrayList<WarpResultItem>();
            var bannerType = banner.getType();
            var playerPity = player.getPity();
            for (int i = 0; i < pullAmount; i++) {
                WarpResultItem item = banner.pull(playerPity);
                pulledItems.add(item);
                playerPity.updatePity(bannerType, item);
            }

            var result = new WarpResult(bannerType, pulledItems);
            var transaction = new WarpTransaction(transactionUuid, player.getId(), Instant.now(), result);
            warpTransactionService.saveTransaction(transaction);
            playerService.savePlayer(player);

            return result;
        } finally {
            warpLock.release(lockName);
        }
    }
}
