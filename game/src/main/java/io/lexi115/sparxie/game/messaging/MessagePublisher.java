package io.lexi115.sparxie.game.messaging;

public interface MessagePublisher {
    <T> void publish(T object, Class<T> clazz, String topic);
}
