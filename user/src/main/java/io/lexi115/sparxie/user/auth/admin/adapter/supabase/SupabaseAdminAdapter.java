package io.lexi115.sparxie.user.auth.admin.adapter.supabase;

import io.lexi115.sparxie.user.auth.adapters.supabase.SupabaseMapper;
import io.lexi115.sparxie.user.auth.admin.AdminAuthenticationAdapter;
import io.lexi115.sparxie.user.auth.admin.dto.AdminCreateUserRequest;
import io.lexi115.sparxie.user.auth.admin.dto.AdminCreateUserResponse;
import io.lexi115.sparxie.user.auth.admin.dto.AdminUpdatePasswordRequest;
import io.lexi115.sparxie.user.auth.admin.dto.AdminViewUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupabaseAdminAdapter implements AdminAuthenticationAdapter {
    private final SupabaseAdminClient supabaseAdminClient;
    private final SupabaseMapper supabaseMapper;

    @Override
    public AdminViewUserResponse viewUser(final UUID userId) {
        var supabaseResponse = supabaseAdminClient.viewUser(userId);
        return supabaseMapper.toClientResponse(supabaseResponse);
    }

    @Override
    public AdminCreateUserResponse createUser(final AdminCreateUserRequest request) {
        var supabaseRequest = supabaseMapper.toSupabaseRequest(request);
        var supabaseResponse = supabaseAdminClient.createUser(supabaseRequest);
        return supabaseMapper.toClientResponse(supabaseResponse);
    }

    @Override
    public void updatePassword(AdminUpdatePasswordRequest request, UUID userId) {
        var supabaseRequest = supabaseMapper.toSupabaseRequest(request);
        supabaseAdminClient.updatePassword(supabaseRequest, userId);
    }

    @Override
    public void deleteUser(UUID userId) {
        supabaseAdminClient.deleteUser(userId);
    }
}
