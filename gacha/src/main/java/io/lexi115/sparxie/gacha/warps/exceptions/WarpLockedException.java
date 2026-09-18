package io.lexi115.sparxie.gacha.warps.exceptions;

public class WarpLockedException extends RuntimeException {
    public WarpLockedException(final String message) {
        super("Warp locked: " + message);
    }
}
