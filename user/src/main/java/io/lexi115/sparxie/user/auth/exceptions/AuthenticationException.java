package io.lexi115.sparxie.user.auth.exceptions;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("An authentication error occurred!");
    }

    public AuthenticationException(String message) {
        super("An authentication error occurred: " + message);
    }
}
