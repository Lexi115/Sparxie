package io.lexi115.sparxie.user.auth.adapter.supabase;

import io.lexi115.sparxie.user.auth.AuthenticationAdapter;
import io.lexi115.sparxie.user.auth.adapter.supabase.dto.SupabaseMapper;
import io.lexi115.sparxie.user.auth.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupabaseAdapter implements AuthenticationAdapter {

    private final SupabaseClient supabaseClient;
    private final SupabaseMapper supabaseMapper;

    @Value("Bearer ${app.supabase.jwt.service}")
    private String serviceBearerToken;

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
    public void updatePassword(UpdatePasswordRequest request, String bearerToken) {
        var supabaseRequest = supabaseMapper.toSupabaseRequest(request);
        supabaseClient.updatePassword(supabaseRequest, bearerToken);
    }

    @Override
    public void adminUpdatePassword(final UpdatePasswordRequest request, final UUID userId) {
        var supabaseRequest = supabaseMapper.toSupabaseRequest(request);
        supabaseClient.adminUpdatePassword(supabaseRequest, userId, serviceBearerToken);
    }

    @Override
    public void adminDelete(final UUID userId) {
        supabaseClient.delete(userId, serviceBearerToken);
    }
}
