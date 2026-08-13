package io.lexi115.sparxie.gacha.system;

import io.lexi115.sparxie.gacha.banner.BannerNotFoundException;
import io.lexi115.sparxie.gacha.banner.BannerPullRequest;
import io.lexi115.sparxie.gacha.banner.BannerPullResult;
import io.lexi115.sparxie.gacha.banner.BannerService;
import io.lexi115.sparxie.gacha.player.PlayerNotFoundException;
import io.lexi115.sparxie.gacha.player.PlayerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GachaService {

    private final BannerService bannerService;
    private final PlayerService playerService;
    private final GachaTransactionRepository gachaTransactionRepository;

    public GachaService(BannerService bannerService, PlayerService playerService, GachaTransactionRepository gachaTransactionRepository) {
        this.bannerService = bannerService;
        this.playerService = playerService;
        this.gachaTransactionRepository = gachaTransactionRepository;
    }

    public List<BannerPullResult> pull(final BannerPullRequest request) {
        // Check cached results if present
        var transaction = gachaTransactionRepository.getById(request.transactionId()).orElse(null);
        if (transaction != null) {
            return transaction.results();
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

        var results = new ArrayList<BannerPullResult>();
        var playerPity = player.getPity();
        for (int i = 0; i < pullAmount; i++) {
            var result = banner.pull(playerPity);
            results.add(result);
            playerPity.updatePity(result);
        }

        transaction = new GachaTransaction(request.transactionId(), results);
        gachaTransactionRepository.save(transaction);
        playerService.savePlayer(player);
        return results;
    }
}
