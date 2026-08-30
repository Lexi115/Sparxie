package io.lexi115.sparxie.user.auth;

import io.lexi115.sparxie.user.auth.dto.RefreshTokenResponse;
import io.lexi115.sparxie.user.auth.dto.UserLoginResponse;
import io.lexi115.sparxie.user.auth.dto.UserRegisterResponse;
import io.lexi115.sparxie.user.auth.event.UserCreatedEvent;
import io.lexi115.sparxie.user.auth.event.UserDeletedEvent;
import io.lexi115.sparxie.user.event.OutboxEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationClient authenticationClient;
    private final OutboxEventService outboxEventService;

    public UserRegisterResponse registerUser(final String username, final String password) {
        var response = authenticationClient.registerUser(username, password);
        var event = new UserCreatedEvent(response.userId(), username, Instant.now());
        outboxEventService.scheduleEvent(event, "user-topic");
        return response;
    }

    public UserLoginResponse authenticateUser(final String username, final String password) {
        return authenticationClient.authenticateUser(username, password);
    }

    public void deleteUser(final UUID userId) {
        authenticationClient.deleteUser(userId);
        var event = new UserDeletedEvent(userId, Instant.now());
        outboxEventService.scheduleEvent(event, "user-topic");
    }

    public RefreshTokenResponse refreshUserToken(final UUID userId, final String refreshToken) {
        return authenticationClient.refreshUserToken(userId, refreshToken);
    }

    public void changeUserPassword(final UUID userId, final String oldPassword, final String newPassword) {
        authenticationClient.changeUserPassword(userId, oldPassword, newPassword);
    }
}
