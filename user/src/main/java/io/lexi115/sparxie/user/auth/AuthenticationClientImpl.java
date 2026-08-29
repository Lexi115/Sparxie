package io.lexi115.sparxie.user.auth;

import io.lexi115.sparxie.user.auth.dto.LoginResponse;
import io.lexi115.sparxie.user.auth.dto.RefreshTokenResponse;
import io.lexi115.sparxie.user.auth.dto.RegisterResponse;
import io.lexi115.sparxie.user.core.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class AuthenticationClientImpl implements AuthenticationClient {

    private final Map<UUID, AuthenticationUser> map = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(AuthenticationClientImpl.class);

    @Override
    public RegisterResponse registerUser(String username, String password) {
        map.forEach((_, user) -> {
            if (user.getUsername().equals(username)) {
                throw new UsernameAlreadyInUseException(username);
            }
        });
        var user = new AuthenticationUser(UUID.randomUUID(), username, Instant.now(), password);
        map.put(user.getId(), user);
        logger.info("[AUTH] Registered user {}", user);
        return new RegisterResponse(user.getId(), "fake-access-token", "fake-refresh-token");
    }

    @Override
    public LoginResponse loginUser(String username, String password) {
        for (var user : map.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                logger.info("[AUTH] Logged user {}", user);
                return new LoginResponse(user.getId(), "fake-access-token", "fake-refresh-token");
            }
        }
        throw new InvalidCredentialsException();
    }

    @Override
    public void deleteUser(UUID userId) {
        var user = map.get(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        map.remove(userId);
        logger.info("[AUTH] Deleted user with ID {}", userId);
    }

    @Override
    public RefreshTokenResponse refreshUserToken(UUID userId, String refreshToken) {
        var user = map.get(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        logger.info("[AUTH] Refreshed token for user {}", user);
        return new RefreshTokenResponse("fake-access-token");
    }

    @Override
    public void changePassword(UUID userId, String oldPassword, String newPassword) {
        var user = map.get(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        if (!user.getPassword().equals(oldPassword)) {
            throw new InvalidCredentialsException("Old password provided doesn't match with current password!");
        }
        user.setPassword(newPassword);
        logger.info("[AUTH] Password changed for user {}", user);
    }
}
