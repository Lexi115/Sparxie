package io.lexi115.sparxie.user.profiles.exceptions;

import java.util.UUID;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException() {
        super("Profile not found!");
    }

    public ProfileNotFoundException(final UUID id) {
        super("Profile with ID '" + id + "' not found.");
    }
}
