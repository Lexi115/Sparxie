package io.lexi115.sparxie.gacha.system;

import io.lexi115.sparxie.gacha.banner.BannerNotFoundException;
import io.lexi115.sparxie.gacha.banner.BannerPullRequest;
import io.lexi115.sparxie.gacha.banner.BannerPullResult;
import io.lexi115.sparxie.gacha.banner.BannerService;
import io.lexi115.sparxie.gacha.event.ItemsPulledEvent;
import io.lexi115.sparxie.gacha.messaging.MessagePublisher;

public class GachaGateway {

    private final BannerService bannerService;
    private final GachaService gachaService;
    private final EconomyClient economyClient;
    private final MessagePublisher messagePublisher;

    public GachaGateway(BannerService bannerService, GachaService gachaService, EconomyClient economyClient, MessagePublisher messagePublisher) {
        this.bannerService = bannerService;
        this.gachaService = gachaService;
        this.economyClient = economyClient;
        this.messagePublisher = messagePublisher;
    }

    public BannerPullResult pull(final BannerPullRequest request) {
        var banner = bannerService.getById(request.bannerId());
        if (banner == null) {
            throw new BannerNotFoundException();
        }
        var playerId = request.playerId();

        // pay
        economyClient.withdraw(playerId, banner.getCurrency(), banner.getCost(request.amount()));

        // pull
        var result = gachaService.pull(request);
        var itemIds = result.items().stream().map(pulledItem -> pulledItem.item().id()).toList();

        // send event to inventory microservice
        var event = new ItemsPulledEvent(playerId, itemIds);
        messagePublisher.publish(event, ItemsPulledEvent.class, "gacha-topic");

        return result;
    }
}
