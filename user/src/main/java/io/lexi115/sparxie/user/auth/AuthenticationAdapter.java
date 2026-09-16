package io.lexi115.sparxie.user.auth;

import io.lexi115.sparxie.user.auth.dto.*;
import io.lexi115.sparxie.user.auth.providers.IdentityProvider;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

public interface AuthenticationAdapter {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    RefreshTokenResponse refreshToken(RefreshTokenRequest request);

    void updatePassword(UpdatePasswordRequest request, String authToken);

    UUID deleteAccount(String authToken);

    URI getAuthorizeUri(IdentityProvider provider);

    CallbackResponse callback(Map<String, String> request);
}
