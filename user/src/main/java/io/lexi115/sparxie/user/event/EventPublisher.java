package io.lexi115.sparxie.user.event;

public interface EventPublisher {
    void publishEvent(String topic, String key, String payload, EventType eventType);
}
