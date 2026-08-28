package io.lexi115.sparxie.user.auth;

import io.lexi115.sparxie.user.auth.dto.LoginResponse;
import io.lexi115.sparxie.user.auth.dto.RefreshTokenResponse;
import io.lexi115.sparxie.user.auth.dto.RegisterResponse;

import java.util.UUID;

public interface AuthenticationClient {
    RegisterResponse registerUser(String username, String password);

    LoginResponse loginUser(String username, String password);

    void deleteUser(UUID id);

    RefreshTokenResponse refreshUserToken(String username, String refreshToken);

    void changePassword(String username, String oldPassword, String newPassword);
}
