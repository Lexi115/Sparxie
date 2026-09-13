package io.lexi115.sparxie.user.auth.adapters.supabase;

import io.lexi115.sparxie.user.auth.AuthenticationAdapter;
import io.lexi115.sparxie.user.auth.adapters.supabase.dto.SupabaseUserMetadata;
import io.lexi115.sparxie.user.auth.admin.adapter.supabase.SupabaseAdminAdapter;
import io.lexi115.sparxie.user.auth.dto.*;
import io.lexi115.sparxie.user.auth.providers.IdentityProvider;
import io.lexi115.sparxie.user.util.CookieHelper;
import io.lexi115.sparxie.user.util.JwtHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupabaseAdapter implements AuthenticationAdapter {

    private final SupabaseClient supabaseClient;
    private final SupabaseAdminAdapter adminAdapter; // Necessary for delete operation.
    private final SupabaseAdapterConfig supabaseAdapterConfig;
    private final SupabaseMapper supabaseMapper;
    private final CookieHelper cookieHelper;
    private final JwtHelper jwtHelper;

    @Override
    public RegisterResponse register(final RegisterRequest request) {
        var supabaseRequest = supabaseMapper.toSupabaseRequest(request);
        var supabaseResponse = supabaseClient.register(supabaseRequest);
        return supabaseMapper.toClientResponse(supabaseResponse, request.username());
    }

    @Override
    public LoginResponse login(final LoginRequest request) {
        var supabaseRequest = supabaseMapper.toSupabaseRequest(request);
        var supabaseResponse = supabaseClient.login(supabaseRequest);
        return supabaseMapper.toClientResponse(supabaseResponse);
    }

    @Override
    public RefreshTokenResponse refreshToken(final RefreshTokenRequest request) {
        var supabaseRequest = supabaseMapper.toSupabaseRequest(request);
        var supabaseResponse = supabaseClient.refreshToken(supabaseRequest);
        return supabaseMapper.toClientResponse(supabaseResponse);

    }

    @Override
    public void updatePassword(final UpdatePasswordRequest request, final String authToken) {
        var supabaseRequest = supabaseMapper.toSupabaseRequest(request);
        supabaseClient.updatePassword(supabaseRequest, authToken);
    }

    @Override
    public UUID deleteAccount(final String authToken) {
        var userId = jwtHelper.extractSubject(authToken, UUID.class);
        adminAdapter.deleteUser(userId);
        return userId;
    }

    @Override
    public URI getAuthorizeUri(final IdentityProvider provider) {
        var baseUri = supabaseAdapterConfig.getSupabaseExternalClientUri();
        return URI.create(baseUri + "/authorize?provider=" + provider.name().toLowerCase());
    }

    @Override
    public CallbackResponse callback(final Map<String, String> params) {
        try (var feignResponse = supabaseClient.callback(params)) {
            var headers = feignResponse.headers();

            var locations = headers.getOrDefault("Location", headers.get("location"));
            var location = locations != null ? URI.create(locations.iterator().next()) : null;

            var cookies = headers.getOrDefault("Set-Cookie", headers.get("set-cookie"));
            var accessToken = cookieHelper.extractCookie(cookies, "sb-access-token");
            var refreshToken = cookieHelper.extractCookie(cookies, "sb-refresh-token");

            var userId = jwtHelper.extractSubject(accessToken, UUID.class);
            var userMetadata = jwtHelper.extractNestedClaim(
                    accessToken, "user_metadata", SupabaseUserMetadata.class);

            return CallbackResponse.builder()
                    .location(location)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .userId(userId)
                    .username(userMetadata.name())
                    .email(userMetadata.email())
                    .createdAt(Instant.now())
                    .build();
        }
    }
}
