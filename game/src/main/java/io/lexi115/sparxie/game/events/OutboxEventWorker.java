package io.lexi115.sparxie.game.events;

import io.lexi115.sparxie.game.events.publishers.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OutboxEventWorker {
    private final EventPublisher eventPublisher;
    private final OutboxEventService outboxEventService;

    @Scheduled(fixedDelay = 2000)
    @Transactional
    public void run() {
        var events = outboxEventService.getSomeOutboxEvents();
        for (OutboxEvent event : events) {
            eventPublisher.publishEvent(event.getTopic(), event.getKey(), event.getPayload(), event.getEventType());
            outboxEventService.deleteOutboxEvent(event);
        }
    }
}
