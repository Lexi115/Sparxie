package io.lexi115.sparxie.user.auth.event;

import io.lexi115.sparxie.user.auth.provider.IdentityProvider;
import io.lexi115.sparxie.user.event.Event;
import io.lexi115.sparxie.user.event.EventType;

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
