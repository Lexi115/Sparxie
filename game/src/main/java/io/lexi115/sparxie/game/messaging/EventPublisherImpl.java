package io.lexi115.sparxie.game.messaging;

import org.springframework.stereotype.Component;

@Component
public class EventPublisherImpl implements EventPublisher {
    @Override
    public void publish(final String topic, final String payload) {
        System.out.println("Sent object " + payload + " to topic '" + topic + "'");
    }
}
