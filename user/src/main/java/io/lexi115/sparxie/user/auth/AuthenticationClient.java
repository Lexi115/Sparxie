package io.lexi115.sparxie.user.auth;

import io.lexi115.sparxie.user.auth.dto.RefreshTokenResponse;
import io.lexi115.sparxie.user.auth.dto.UserLoginResponse;
import io.lexi115.sparxie.user.auth.dto.UserRegisterResponse;

import java.util.UUID;

public interface AuthenticationClient {
    UserRegisterResponse registerUser(String username, String password);

    UserLoginResponse authenticateUser(String username, String password);

    void deleteUser(UUID userId);

    RefreshTokenResponse refreshUserToken(UUID userId, String refreshToken);

    void changeUserPassword(UUID userId, String oldPassword, String newPassword);
}
