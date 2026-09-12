package io.lexi115.sparxie.user.auth.provider;

public class InvalidProviderException extends RuntimeException {
    public InvalidProviderException(final String message) {
        super("Invalid provider: " + message);
    }
}
