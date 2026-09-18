package io.lexi115.sparxie.logger.warps.events;

import io.lexi115.sparxie.logger.warps.WarpService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "${app.kafka.topic.warp}", groupId = "${app.kafka.group.logger}")
@RequiredArgsConstructor
public class KafkaWarpConsumer {
    private final WarpService warpService;

    @KafkaHandler
    public void onWarpPerformed(final WarpPerformedEvent event) {
        warpService.createWarpPerformed(
                event.transactionId(),
                event.playerId(),
                event.createdAt(),
                event.result()
        );
    }
}
