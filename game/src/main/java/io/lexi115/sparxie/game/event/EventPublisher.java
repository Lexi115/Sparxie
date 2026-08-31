package io.lexi115.sparxie.game.event;

public interface EventPublisher {
    void publishEvent(String topic, String key, String payload, EventType eventType);
}
