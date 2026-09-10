package io.lexi115.sparxie.user.auth;

import io.lexi115.sparxie.user.auth.dto.*;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

public interface AuthenticationAdapter {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);

    void updatePassword(UpdatePasswordRequest request, String bearerToken);

    void delete(UUID userId);

    URI getAuthorizeUri(IdentityProvider provider);

    CallbackResponse callback(Map<String, String> request);
}
