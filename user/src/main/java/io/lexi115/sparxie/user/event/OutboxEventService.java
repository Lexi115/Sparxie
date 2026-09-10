package io.lexi115.sparxie.user.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutboxEventService {
    private final OutboxEventRepository outboxEventRepository;
    private final ObjectMapper objectMapper;

    public List<OutboxEvent> getSomeOutboxEvents() {
        return (List<OutboxEvent>) outboxEventRepository.findAll();
    }

    public void scheduleEvent(final Event event, final String key, final String topic) {
        try {
            var outboxEvent = OutboxEvent.builder()
                    .id(UUID.randomUUID())
                    .key(key)
                    .topic(topic)
                    .payload(objectMapper.writeValueAsString(event))
                    .eventType(event.getEventType())
                    .build();
            outboxEventRepository.save(outboxEvent);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize Outbox Event", e);
        }
    }

    public void deleteOutboxEvent(final OutboxEvent outboxEvent) {
        outboxEventRepository.delete(outboxEvent);
    }
}
