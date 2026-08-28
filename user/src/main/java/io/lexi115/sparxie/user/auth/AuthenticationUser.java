package io.lexi115.sparxie.user.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public final class AuthenticationUser {
    private final UUID id;
    private final String username;
    private final Instant createdAt;
    private String password;
}
