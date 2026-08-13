package io.lexi115.sparxie.game.messaging;

import org.springframework.stereotype.Component;

@Component
public class MessagePublisherImpl implements MessagePublisher {
    @Override
    public <T> void publish(T object, Class<T> clazz, String topic) {
        System.out.println("Sent object " + object + " to topic '" + topic + "'");
    }
}
