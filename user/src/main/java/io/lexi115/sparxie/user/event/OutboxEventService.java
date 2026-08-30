package io.lexi115.sparxie.user.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutboxEventService {
    private final OutboxEventRepository outboxEventRepository;
    private final ObjectMapper objectMapper;

    public List<OutboxEvent> getSomeOutboxEvents() {
        return outboxEventRepository.findTop100ByOrderByCreatedAtAsc();
    }

    public void scheduleEvent(final Event event, final String key, final String topic, final EventType eventType) {
        try {
            var outboxEvent = new OutboxEvent(
                    UUID.randomUUID(),
                    key,
                    topic,
                    objectMapper.writeValueAsString(event),
                    Instant.now(),
                    eventType
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
