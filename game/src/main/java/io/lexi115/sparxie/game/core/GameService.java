package io.lexi115.sparxie.game.core;

import io.lexi115.sparxie.game.banner.BannerNotFoundException;
import io.lexi115.sparxie.game.banner.BannerService;
import io.lexi115.sparxie.game.economy.EconomyService;
import io.lexi115.sparxie.game.messaging.MessagePublisher;
import io.lexi115.sparxie.game.warp.WarpService;
import io.lexi115.sparxie.game.warp.dto.WarpRequest;
import io.lexi115.sparxie.game.warp.dto.WarpResultDto;
import io.lexi115.sparxie.game.warp.dto.WarpResultItemDto;
import io.lexi115.sparxie.game.warp.event.WarpPerformedEvent;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final BannerService bannerService;
    private final WarpService warpService;
    private final EconomyService economyService;
    private final MessagePublisher messagePublisher;

    public GameService(
            final BannerService bannerService,
            final WarpService warpService,
            final EconomyService economyService,
            final MessagePublisher messagePublisher
    ) {
        this.bannerService = bannerService;
        this.warpService = warpService;
        this.economyService = economyService;
        this.messagePublisher = messagePublisher;
    }

    public WarpResultDto performWarp(final WarpRequest request) {
        var bannerDetails = bannerService.getDetailsById(request.bannerId());
        if (bannerDetails == null) {
            throw new BannerNotFoundException();
        }
        var playerId = request.playerId();

        // pay
        economyService.withdraw(playerId, bannerDetails.currency(), bannerDetails.getCost(request.amount()));

        // pull
        var result = warpService.pull(request);
        var itemIds = result.items().stream().map(WarpResultItemDto::itemId).toList();

        // send event to inventory microservice
        var event = new WarpPerformedEvent(playerId, itemIds);
        messagePublisher.publish(event, WarpPerformedEvent.class, "gacha-topic");

        return result;
    }
}
