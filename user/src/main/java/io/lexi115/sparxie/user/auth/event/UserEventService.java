package io.lexi115.sparxie.user.auth.event;

import io.lexi115.sparxie.user.auth.provider.IdentityProvider;
import io.lexi115.sparxie.user.event.OutboxEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserEventService {
    private final OutboxEventService outboxEventService;
    private final UserEventConfig userEventConfig;

    public void userCreated(
            final UUID userId,
            final String username,
            final Instant createdAt,
            final IdentityProvider provider
    ) {
        var event = new UserCreatedEvent(userId, username, createdAt, provider);
        outboxEventService.scheduleEvent(event, userId.toString(), userEventConfig.getUserTopicName());
    }

    public void userDeleted(final UUID userId, final Instant deletedAt) {
        var event = new UserDeletedEvent(userId, deletedAt);
        outboxEventService.scheduleEvent(event, userId.toString(), userEventConfig.getUserTopicName());
    }
}
