package io.lexi115.sparxie.user.auth;

public class UsernameAlreadyInUseException extends RuntimeException {
    public UsernameAlreadyInUseException(final String username) {
        super("Username '" + username + "' already in use, please choose a different name!");
    }
}
