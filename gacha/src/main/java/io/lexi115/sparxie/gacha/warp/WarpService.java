package io.lexi115.sparxie.gacha.warp;

import io.lexi115.sparxie.gacha.banner.BannerNotFoundException;
import io.lexi115.sparxie.gacha.banner.BannerService;
import io.lexi115.sparxie.gacha.player.PlayerNotFoundException;
import io.lexi115.sparxie.gacha.player.PlayerService;
import io.lexi115.sparxie.gacha.warp.dto.WarpRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class WarpService {

    private final BannerService bannerService;
    private final PlayerService playerService;
    private final WarpTransactionRepository warpTransactionRepository;

    public WarpService(BannerService bannerService, PlayerService playerService, WarpTransactionRepository warpTransactionRepository) {
        this.bannerService = bannerService;
        this.playerService = playerService;
        this.warpTransactionRepository = warpTransactionRepository;
    }

    public WarpResult pull(final WarpRequest request) {
        // Check cached result if present
        var transactionUuid = UUID.fromString(request.transactionId());
        var cachedTransaction = warpTransactionRepository.getById(transactionUuid).orElse(null);
        if (cachedTransaction != null) {
            return cachedTransaction.result();
        }

        var player = playerService.getById(request.playerId());
        if (player == null) {
            throw new PlayerNotFoundException();
        }
        var banner = bannerService.getById(request.bannerId());
        if (banner == null) {
            throw new BannerNotFoundException();
        }
        var pullAmount = request.amount();
        if (pullAmount <= 0) {
            throw new IllegalArgumentException("Amount of pulls cannot be 0 or less");
        }

        var pulledItems = new ArrayList<WarpResultItem>();
        var bannerType = banner.getType();
        var playerPity = player.getPity();
        WarpResultItem item;
        for (int i = 0; i < pullAmount; i++) {
            item = banner.pull(playerPity);
            pulledItems.add(item);
            playerPity.updatePity(bannerType, item);
        }

        var result = new WarpResult(bannerType, pulledItems);
        var transaction = new WarpTransaction(transactionUuid, player.getId(), Instant.now(), result);
        warpTransactionRepository.save(transaction);
        playerService.savePlayer(player);
        return result;
    }
}
