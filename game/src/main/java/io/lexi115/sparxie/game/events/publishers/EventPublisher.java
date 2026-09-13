package io.lexi115.sparxie.game.events.publishers;

import io.lexi115.sparxie.game.events.EventType;

public interface EventPublisher {
    void publishEvent(String topic, String key, String payload, EventType eventType);
}
