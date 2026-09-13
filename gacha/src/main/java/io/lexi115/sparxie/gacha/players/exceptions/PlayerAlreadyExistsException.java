package io.lexi115.sparxie.gacha.players.exceptions;

import java.util.UUID;

public class PlayerAlreadyExistsException extends RuntimeException {
    public PlayerAlreadyExistsException(UUID playerId) {
        super("ID '" + playerId + "' already in use.");
    }
}
