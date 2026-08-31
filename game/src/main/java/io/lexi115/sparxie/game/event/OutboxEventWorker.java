package io.lexi115.sparxie.game.event;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutboxEventWorker {
    private final EventPublisher eventPublisher;
    private final OutboxEventService outboxEventService;

    @Scheduled(fixedDelay = 2000)
    public void run() {
        var events = outboxEventService.getSomeOutboxEvents();
        for (OutboxEvent event : events) {
            try {
                eventPublisher.publishEvent(event.topic(), event.key(), event.payload(), event.type());
                outboxEventService.deleteOutboxEvent(event);
            } catch (Exception e) {
                break; // message broker is down
            }
        }
    }
}
