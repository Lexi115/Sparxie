package io.lexi115.sparxie.user.auth;

import io.lexi115.sparxie.user.auth.dto.*;
import io.lexi115.sparxie.user.auth.events.UserEventService;
import io.lexi115.sparxie.user.auth.providers.IdentityProvider;
import io.lexi115.sparxie.user.auth.providers.InvalidProviderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationAdapter authenticationAdapter;
    private final UserEventService userEventService;

    public RegisterResponse register(final RegisterRequest request) {
        var response = authenticationAdapter.register(request);
        userEventService.userCreated(response.userId(), response.username(), response.createdAt(), IdentityProvider.EMAIL);
        return response;
    }

    public LoginResponse login(final LoginRequest request) {
        return authenticationAdapter.login(request);
    }

    public RefreshTokenResponse refreshToken(final RefreshTokenRequest request) {
        return authenticationAdapter.refreshToken(request);
    }

    public void updatePassword(final UpdatePasswordRequest request, final String authToken) {
        authenticationAdapter.updatePassword(request, authToken);
    }

    public void deleteAccount(final String authToken) {
        var deletedUserId = authenticationAdapter.deleteAccount(authToken);
        userEventService.userDeleted(deletedUserId, Instant.now());
    }

    public URI getAuthorizeUri(final IdentityProvider provider) {
        if (provider == IdentityProvider.EMAIL) {
            throw new InvalidProviderException(provider.name().toLowerCase());
        }
        return authenticationAdapter.getAuthorizeUri(provider);
    }

    public CallbackResponse callback(final Map<String, String> params, final IdentityProvider provider) {
        var response = authenticationAdapter.callback(params);
        userEventService.userCreated(response.userId(), response.username(), response.createdAt(), provider);
        return response;
    }
}
