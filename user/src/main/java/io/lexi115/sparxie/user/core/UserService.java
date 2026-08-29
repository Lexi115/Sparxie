package io.lexi115.sparxie.user.core;

import io.lexi115.sparxie.user.auth.AuthenticationService;
import io.lexi115.sparxie.user.auth.dto.LoginResponse;
import io.lexi115.sparxie.user.auth.dto.RefreshTokenResponse;
import io.lexi115.sparxie.user.auth.dto.RegisterResponse;
import io.lexi115.sparxie.user.core.event.UserCreatedEvent;
import io.lexi115.sparxie.user.core.event.UserDeletedEvent;
import io.lexi115.sparxie.user.messaging.OutboxEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final OutboxEventService outboxEventService;

    public User getById(final UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public RegisterResponse registerUser(final String username, final String password) {
        var response = authenticationService.registerUser(username, password);
        var user = new User(response.userId(), username, Instant.now());
        userRepository.save(user);
        var event = new UserCreatedEvent(user.getId(), user.getUsername(), user.getCreatedAt());
        outboxEventService.scheduleEvent(event, "user-topic");
        return response;
    }

    public LoginResponse loginUser(final String username, final String password) {
        return authenticationService.loginUser(username, password);
    }

    public void deleteUser(final UUID id) {
        authenticationService.deleteUser(id);
        userRepository.deleteById(id);
        var event = new UserDeletedEvent(id, Instant.now());
        outboxEventService.scheduleEvent(event, "user-topic");
    }

    public RefreshTokenResponse refreshUserToken(final UUID userId, final String refreshToken) {
        return authenticationService.refreshUserToken(userId, refreshToken);
    }

    public void changePassword(final UUID userId, final String oldPassword, final String newPassword) {
        authenticationService.changePassword(userId, oldPassword, newPassword);
    }
}
