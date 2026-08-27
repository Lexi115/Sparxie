package io.lexi115.sparxie.inventory.player;

import java.util.UUID;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(final UUID playerId) {
        super("Player with ID '" + playerId + "' not found.");
    }
}
