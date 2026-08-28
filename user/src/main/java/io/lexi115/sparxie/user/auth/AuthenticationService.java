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

    public RegisterResponse registerUser(String username, String password) {
        return authenticationClient.registerUser(username, password);
    }

    public LoginResponse loginUser(String username, String password) {
        return authenticationClient.loginUser(username, password);
    }

    public void deleteUser(UUID id) {
        authenticationClient.deleteUser(id);
    }

    public RefreshTokenResponse refreshUserToken(String username, String refreshToken) {
        return authenticationClient.refreshUserToken(username, refreshToken);
    }
}
