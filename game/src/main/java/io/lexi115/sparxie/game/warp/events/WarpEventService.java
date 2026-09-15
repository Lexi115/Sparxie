package io.lexi115.sparxie.game.warp.events;

import io.lexi115.sparxie.game.events.OutboxEventService;
import io.lexi115.sparxie.game.game.dto.WarpResponse;
import io.lexi115.sparxie.game.warp.transactions.WarpTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarpEventService {
    private final OutboxEventService outboxEventService;

    @Value("${app.kafka.topic.warp}")
    private String warpTopicName;

    public void warpPerformed(final WarpTransaction transaction) {
        warpPerformed(
                transaction.getTransactionId(),
                transaction.getPlayerId(),
                transaction.getCreatedAt(),
                transaction.getResult()
        );
    }

    public void warpPerformed(
            final UUID transactionId,
            final UUID playerId,
            final Instant createdAt,
            final WarpResponse result
    ) {
        var event = new WarpPerformedEvent(transactionId, playerId, createdAt, result);
        outboxEventService.scheduleEvent(event, playerId.toString(), warpTopicName);
    }
}
