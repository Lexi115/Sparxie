package io.lexi115.sparxie.logger.shop.events;

import io.lexi115.sparxie.logger.shop.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "${app.kafka.topic.shop}", groupId = "${app.kafka.group.logger}")
@RequiredArgsConstructor
public class KafkaShopConsumer {
    private final ShopService shopService;

    @KafkaHandler
    public void onPurchasePerformed(final PurchasePerformedEvent event) {
        shopService.createPurchasePerformed(
                event.transactionId(),
                event.playerId(),
                event.createdAt(),
                event.currency(),
                event.price(),
                event.itemId(),
                event.amount()
        );
    }
}
