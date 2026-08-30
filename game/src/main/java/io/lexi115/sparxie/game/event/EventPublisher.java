package io.lexi115.sparxie.game.event;

public interface EventPublisher {
    void publish(String topic, String payload);
}
