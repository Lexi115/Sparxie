package io.lexi115.sparxie.game.players.exceptions;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(final String message) {
        super(message);
    }
}
