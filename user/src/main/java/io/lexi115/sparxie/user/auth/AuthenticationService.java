package io.lexi115.sparxie.user.auth;

import io.lexi115.sparxie.user.auth.dto.LoginResponse;
import io.lexi115.sparxie.user.auth.dto.RefreshTokenResponse;
import io.lexi115.sparxie.user.auth.dto.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationClient authenticationClient;

    public RegisterResponse registerUser(final String username, final String password) {
        return authenticationClient.registerUser(username, password);
    }

    public LoginResponse loginUser(final String username, final String password) {
        return authenticationClient.loginUser(username, password);
    }

    public void deleteUser(final UUID userId) {
        authenticationClient.deleteUser(userId);
    }

    public RefreshTokenResponse refreshUserToken(final UUID userId, final String refreshToken) {
        return authenticationClient.refreshUserToken(userId, refreshToken);
    }

    public void changePassword(final UUID userId, final String oldPassword, final String newPassword) {
        authenticationClient.changePassword(userId, oldPassword, newPassword);
    }
}
