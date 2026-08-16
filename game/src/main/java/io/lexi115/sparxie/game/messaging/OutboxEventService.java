package io.lexi115.sparxie.game.messaging;

import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class OutboxEventService {
    private final OutboxEventRepository outboxEventRepository;
    private final ObjectMapper objectMapper;

    public OutboxEventService(final OutboxEventRepository outboxEventRepository, final ObjectMapper objectMapper) {
        this.outboxEventRepository = outboxEventRepository;
        this.objectMapper = objectMapper;
    }

    public List<OutboxEvent> getSomeOutboxEvents() {
        return outboxEventRepository.findTop100ByOrderByCreatedAtAsc();
    }

    public void scheduleEvent(final Event event, final String topic) {
        try {
            var outboxEvent = new OutboxEvent(
                    UUID.randomUUID(),
                    topic,
                    objectMapper.writeValueAsString(event),
                    Instant.now()
            );
            outboxEventRepository.save(outboxEvent);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize Outbox Event", e);
        }
    }

    public void deleteOutboxEvent(final OutboxEvent outboxEvent) {
        outboxEventRepository.delete(outboxEvent);
    }
}
