package io.lexi115.sparxie.user.auth.exception;

public class InvalidCredentialsException extends AuthenticationException {
    public InvalidCredentialsException() {
        super("The provided credentials are not valid!");
    }

    public InvalidCredentialsException(final String message) {
        super(message);
    }
}
