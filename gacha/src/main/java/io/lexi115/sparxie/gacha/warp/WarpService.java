package io.lexi115.sparxie.gacha.warp;

import io.lexi115.sparxie.gacha.banners.Banner;
import io.lexi115.sparxie.gacha.banners.BannerService;
import io.lexi115.sparxie.gacha.concurrent.Lock;
import io.lexi115.sparxie.gacha.players.Player;
import io.lexi115.sparxie.gacha.players.PlayerService;
import io.lexi115.sparxie.gacha.warp.dto.WarpRequest;
import io.lexi115.sparxie.gacha.warp.exceptions.WarpLockedException;
import io.lexi115.sparxie.gacha.warp.transactions.WarpTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarpService {
    private final BannerService bannerService;
    private final PlayerService playerService;
    private final WarpTransactionService warpTransactionService;
    private final Lock playerLock;

    @Transactional
    public WarpResult pull(final WarpRequest request, final UUID playerId) {
        var transactionId = request.transactionId();
        var lockName = "warp_lock_" + playerId;
        if (!playerLock.acquire(lockName)) {
            throw new WarpLockedException("Please wait a bit before making another pull!");
        }
        try {
            var player = playerService.getById(playerId);
            var banner = bannerService.getBanner(request.bannerId());
            var cachedTransaction = warpTransactionService.getById(transactionId);
            if (cachedTransaction != null) {
                return cachedTransaction.getResult();
            }
            var result = pullItems(banner, player, request.amount());
            warpTransactionService.create(transactionId, playerId, result);
            playerService.save(player);
            return result;
        } finally {
            playerLock.release(lockName);
        }
    }

    private WarpResult pullItems(final Banner banner, final Player player, final Integer amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount of pulls cannot be 0 or less.");
        }
        var pulledItems = new ArrayList<WarpResultItem>();
        var bannerType = banner.getType();
        var playerPity = player.getPity();
        for (int i = 0; i < amount; i++) {
            var item = banner.pullItem(playerPity);
            pulledItems.add(item);
            playerPity.update(bannerType, item);
        }
        return new WarpResult(bannerType, pulledItems);
    }
}
