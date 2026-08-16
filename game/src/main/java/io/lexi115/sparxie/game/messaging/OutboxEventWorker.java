package io.lexi115.sparxie.game.messaging;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OutboxEventWorker {
    private final EventPublisher eventPublisher;
    private final OutboxEventService outboxEventService;

    public OutboxEventWorker(final EventPublisher eventPublisher, final OutboxEventService outboxEventService) {
        this.eventPublisher = eventPublisher;
        this.outboxEventService = outboxEventService;
        System.out.println("outbox event worker online!");
    }

    @Scheduled(fixedDelay = 2000)
    public void run() {
        var events = outboxEventService.getSomeOutboxEvents();
        for (OutboxEvent e : events) {
            try {
                eventPublisher.publish(e.topic(), e.payload());
                outboxEventService.deleteOutboxEvent(e);
            } catch (Exception ex) {
                break; // message broker is down
            }
        }
    }
}
