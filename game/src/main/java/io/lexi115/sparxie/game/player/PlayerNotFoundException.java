package io.lexi115.sparxie.game.player;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(final String message) {
        super(message);
    }
}
