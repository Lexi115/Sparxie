package io.lexi115.sparxie.game.system;

import io.lexi115.sparxie.game.banner.*;
import io.lexi115.sparxie.game.economy.EconomyClient;
import io.lexi115.sparxie.game.event.ItemsPulledEvent;
import io.lexi115.sparxie.game.messaging.MessagePublisher;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final BannerClient bannerClient;
    private final GachaClient gachaClient;
    private final EconomyClient economyClient;
    private final MessagePublisher messagePublisher;

    public GameService(final BannerClient bannerClient, GachaClient gachaClient, EconomyClient economyClient, MessagePublisher messagePublisher) {
        this.bannerClient = bannerClient;
        this.gachaClient = gachaClient;
        this.economyClient = economyClient;
        this.messagePublisher = messagePublisher;
    }

    public BannerPullResultDto performWarp(final BannerPullRequest request) {
        var bannerDetails = bannerClient.getDetailsById(request.bannerId());
        if (bannerDetails == null) {
            throw new BannerNotFoundException();
        }
        var playerId = request.playerId();

        // pay
        economyClient.withdraw(playerId, bannerDetails.currency(), bannerDetails.getCost(request.amount()));

        // pull
        var result = gachaClient.pull(request);
        var itemIds = result.items().stream().map(PulledBannerItemDto::itemId).toList();

        // send event to inventory microservice
        var event = new ItemsPulledEvent(playerId, itemIds);
        messagePublisher.publish(event, ItemsPulledEvent.class, "gacha-topic");

        return result;
    }
}
