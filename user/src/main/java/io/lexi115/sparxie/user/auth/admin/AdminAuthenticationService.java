package io.lexi115.sparxie.user.auth.admin;

import io.lexi115.sparxie.user.auth.admin.dto.AdminCreateUserRequest;
import io.lexi115.sparxie.user.auth.admin.dto.AdminCreateUserResponse;
import io.lexi115.sparxie.user.auth.admin.dto.AdminUpdatePasswordRequest;
import io.lexi115.sparxie.user.auth.event.UserCreatedEvent;
import io.lexi115.sparxie.user.auth.event.UserDeletedEvent;
import io.lexi115.sparxie.user.event.OutboxEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminAuthenticationService {
    private final AdminAuthenticationAdapter adminAuthenticationAdapter;
    private final OutboxEventService outboxEventService;

    @Value("${app.kafka.topic.user}")
    private String userTopicName;

    public AdminCreateUserResponse createUser(final AdminCreateUserRequest request) {
        var response = adminAuthenticationAdapter.createUser(request);
        var userId = response.userId();
        var event = new UserCreatedEvent(userId, response.username(), response.createdAt());
        outboxEventService.scheduleEvent(event, userId.toString(), userTopicName);
        return response;
    }

    public void updatePassword(final AdminUpdatePasswordRequest request, final UUID userId) {
        adminAuthenticationAdapter.updatePassword(request, userId);
    }

    public void deleteUser(final UUID userId) {
        adminAuthenticationAdapter.deleteUser(userId);
        var event = new UserDeletedEvent(userId, Instant.now());
        outboxEventService.scheduleEvent(event, userId.toString(), userTopicName);
    }
}
