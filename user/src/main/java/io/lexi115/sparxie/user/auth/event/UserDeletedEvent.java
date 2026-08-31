package io.lexi115.sparxie.user.auth.event;

import io.lexi115.sparxie.user.event.Event;
import io.lexi115.sparxie.user.event.EventType;

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
