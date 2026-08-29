package io.lexi115.sparxie.inventory.player.event;

import io.lexi115.sparxie.inventory.player.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerEventConsumer {
    private final PlayerService playerService;

    public void onPlayerCreated(final PlayerCreatedEvent event) {
        System.out.println("player created event");
        playerService.createPlayer(event.userId());
    }

    public void onPlayerDeleted(final PlayerDeletedEvent event) {
        System.out.println("player deleted event");
        playerService.deleteById(event.userId());
    }
}
