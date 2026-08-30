package io.lexi115.sparxie.user.event;

public interface EventPublisher {
    void publish(String topic, String payload);
}
