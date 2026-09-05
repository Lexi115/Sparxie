package io.lexi115.sparxie.user.auth;

import io.lexi115.sparxie.user.auth.dto.*;

import java.util.UUID;

public interface AuthenticationAdapter {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);

    void updatePassword(UUID userId, UpdatePasswordRequest request);

    void delete(UUID userId);
}
