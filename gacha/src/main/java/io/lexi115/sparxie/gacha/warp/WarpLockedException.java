package io.lexi115.sparxie.gacha.warp;

public class WarpLockedException extends RuntimeException {
    public WarpLockedException(final String message) {
        super("Warp locked: " + message);
    }
}
