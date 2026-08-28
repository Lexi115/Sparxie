package io.lexi115.sparxie.user.auth;

import io.lexi115.sparxie.user.auth.dto.LoginResponse;
import io.lexi115.sparxie.user.auth.dto.RefreshTokenResponse;
import io.lexi115.sparxie.user.auth.dto.RegisterResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class AuthenticationClientImpl implements AuthenticationClient {

    private final Map<UUID, AuthenticationUser> map = new HashMap<>();

    @Override
    public RegisterResponse registerUser(String username, String password) {
        map.forEach((_, user) -> {
            if (user.getUsername().equals(username)) {
                throw new AuthenticationException("Username already in use!");
            }
        });
        var user = new AuthenticationUser(UUID.randomUUID(), username, Instant.now(), password);
        map.put(user.getId(), user);
        return new RegisterResponse(user.getId(), "fake-access-token", "fake-refresh-token");
    }

    @Override
    public LoginResponse loginUser(String username, String password) {
        for (var user : map.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return new LoginResponse(user.getId(), "fake-access-token", "fake-refresh-token");
            }
        }
        throw new AuthenticationException("Invalid credentials!");
    }

    @Override
    public void deleteUser(UUID id) {
        map.remove(id);
    }

    @Override
    public RefreshTokenResponse refreshUserToken(String username, String refreshToken) {
        for (var user : map.values()) {
            if (user.getUsername().equals(username)) {
                return new RefreshTokenResponse("fake-access-token");
            }
        }
        throw new AuthenticationException("Invalid credentials!");
    }

    @Override
    public void changePassword(String username, String oldPassword, String newPassword) {
        for (var user : map.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(oldPassword)) {
                user.setPassword(newPassword);
                return;
            }
        }
        throw new AuthenticationException("Invalid credentials!");
    }
}
