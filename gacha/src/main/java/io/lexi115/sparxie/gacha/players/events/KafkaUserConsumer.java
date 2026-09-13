package io.lexi115.sparxie.gacha.players.events;

import io.lexi115.sparxie.gacha.players.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = "${app.kafka.topic.user}", groupId = "${app.kafka.group.gacha}")
public class KafkaUserConsumer {
    private final PlayerService playerService;

    @KafkaHandler
    public void onUserCreated(final UserCreatedEvent event) {
        System.out.println("player created event " + event.userId());
        var userId = event.userId();
        if (!playerService.existsById(userId)) {
            playerService.create(event.userId());
        }
    }

    @KafkaHandler
    public void onUserDeleted(final UserDeletedEvent event) {
        System.out.println("player deleted event " + event.userId());
        playerService.deleteById(event.userId());
    }
}
