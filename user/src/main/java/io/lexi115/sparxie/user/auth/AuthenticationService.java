package io.lexi115.sparxie.user.auth;

import io.lexi115.sparxie.user.auth.dto.*;
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
public class AuthenticationService {
    private final AuthenticationAdapter authenticationAdapter;
    private final OutboxEventService outboxEventService;

    @Value("${app.kafka.topic.user}")
    private String userTopicName;

    public RegisterResponse register(final RegisterRequest request) {
        var response = authenticationAdapter.register(request);
        var userId = response.userId();
        var event = new UserCreatedEvent(userId, request.username(), Instant.now());
        outboxEventService.scheduleEvent(event, userId.toString(), userTopicName);
        return response;
    }

    public LoginResponse login(final LoginRequest request) {
        return authenticationAdapter.login(request);
    }

    public RefreshTokenResponse refreshToken(final RefreshTokenRequest request) {
        return authenticationAdapter.refreshToken(request);
    }

    public void updatePassword(final UUID userId, final UpdatePasswordRequest request) {
        authenticationAdapter.updatePassword(userId, request);
    }

    public void delete(final UUID userId) {
        authenticationAdapter.delete(userId);
        var event = new UserDeletedEvent(userId, Instant.now());
        outboxEventService.scheduleEvent(event, userId.toString(), userTopicName);
    }
}
