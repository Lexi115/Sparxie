package io.lexi115.sparxie.user.messaging;

public interface EventPublisher {
    void publish(String topic, String payload);
}
