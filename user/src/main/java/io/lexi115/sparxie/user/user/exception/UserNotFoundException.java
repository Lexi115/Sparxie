package io.lexi115.sparxie.user.user.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found!");
    }

    public UserNotFoundException(final UUID id) {
        super("User with ID '" + id + "' not found.");
    }
}
