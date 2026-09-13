package io.lexi115.sparxie.user.events;

public interface EventPublisher {
    void publishEvent(String topic, String key, String payload, EventType eventType);
}
