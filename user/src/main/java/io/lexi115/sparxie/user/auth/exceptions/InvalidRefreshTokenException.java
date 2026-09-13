package io.lexi115.sparxie.user.auth.exceptions;

public class InvalidRefreshTokenException extends RuntimeException {
    public InvalidRefreshTokenException() {
        super("Invalid refresh token!");
    }
}
