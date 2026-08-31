package io.lexi115.sparxie.inventory.player.event;

import io.lexi115.sparxie.inventory.player.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = "${app.kafka.topic.user}", groupId = "${app.kafka.group.inventory}")
public class KafkaPlayerConsumer {
    private final PlayerService playerService;

    @KafkaHandler
    public void onPlayerCreated(final PlayerCreatedEvent event) {
        System.out.println("player created event " + event.userId());
        playerService.createPlayer(event.userId());
    }

    @KafkaHandler
    public void onPlayerDeleted(final PlayerDeletedEvent event) {
        System.out.println("player deleted event " + event.userId());
        playerService.deletePlayer(event.userId());
    }
}
