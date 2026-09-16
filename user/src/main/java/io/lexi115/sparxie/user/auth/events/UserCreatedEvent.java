package io.lexi115.sparxie.user.auth.events;

import io.lexi115.sparxie.user.auth.providers.IdentityProvider;
import io.lexi115.sparxie.user.events.Event;
import io.lexi115.sparxie.user.events.EventType;

import java.time.Instant;
import java.util.UUID;

public record UserCreatedEvent(
        UUID userId,
        String username,
        Instant createdAt,
        IdentityProvider provider
) implements Event {
    @Override
    public EventType getEventType() {
        return EventType.USER_CREATED;
    }
}
