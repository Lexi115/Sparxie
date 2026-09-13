package io.lexi115.sparxie.user.auth.events;

import io.lexi115.sparxie.user.events.Event;
import io.lexi115.sparxie.user.events.EventType;

import java.time.Instant;
import java.util.UUID;

public record UserDeletedEvent(
        UUID userId,
        Instant deletedAt
) implements Event {
    @Override
    public EventType getEventType() {
        return EventType.USER_DELETED;
    }
}
