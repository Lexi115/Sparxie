package io.lexi115.sparxie.user.auth;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super("An authentication error occurred: " + message);
    }
}
