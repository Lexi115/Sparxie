package io.lexi115.sparxie.user.auth.adapters.supabase;

import io.lexi115.sparxie.user.auth.adapters.supabase.dto.*;
import io.lexi115.sparxie.user.auth.admin.adapter.supabase.dto.SupabaseAdminCreateUserRequest;
import io.lexi115.sparxie.user.auth.admin.adapter.supabase.dto.SupabaseAdminCreateUserResponse;
import io.lexi115.sparxie.user.auth.admin.adapter.supabase.dto.SupabaseAdminUpdatePasswordRequest;
import io.lexi115.sparxie.user.auth.admin.adapter.supabase.dto.SupabaseAdminViewUserResponse;
import io.lexi115.sparxie.user.auth.admin.dto.AdminCreateUserRequest;
import io.lexi115.sparxie.user.auth.admin.dto.AdminCreateUserResponse;
import io.lexi115.sparxie.user.auth.admin.dto.AdminUpdatePasswordRequest;
import io.lexi115.sparxie.user.auth.admin.dto.AdminViewUserResponse;
import io.lexi115.sparxie.user.auth.dto.*;
import io.lexi115.sparxie.user.auth.providers.IdentityProvider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupabaseMapper {
    SupabaseRegisterRequest toSupabaseRequest(RegisterRequest request);

    @Mapping(target = "userId", source = "response.user.id")
    @Mapping(target = "email", source = "response.user.email")
    @Mapping(target = "createdAt", source = "response.user.createdAt")
    @Mapping(target = "providers", source = "response.user.identities")
    RegisterResponse toClientResponse(SupabaseRegisterResponse response, String username);

    SupabaseLoginRequest toSupabaseRequest(LoginRequest request);

    @Mapping(target = "userId", source = "response.user.id")
    @Mapping(target = "email", source = "response.user.email")
    @Mapping(target = "createdAt", source = "response.user.createdAt")
    @Mapping(target = "providers", source = "response.user.identities")
    LoginResponse toClientResponse(SupabaseLoginResponse response);

    SupabaseRefreshTokenRequest toSupabaseRequest(RefreshTokenRequest request);

    RefreshTokenResponse toClientResponse(SupabaseRefreshTokenResponse response);

    SupabaseUpdatePasswordRequest toSupabaseRequest(UpdatePasswordRequest request);

    // ---- ADMIN ----

    @Mapping(target = "username", source = "userMetadata.username")
    @Mapping(target = "roles", source = "appMetadata.roles")
    @Mapping(target = "providers", source = "response.identities")
    AdminViewUserResponse toClientResponse(SupabaseAdminViewUserResponse response);

    @Mapping(target = "userMetadata.username", source = "username")
    @Mapping(target = "userMetadata.email", source = "email")
    @Mapping(target = "appMetadata.roles", source = "roles")
    SupabaseAdminCreateUserRequest toSupabaseRequest(AdminCreateUserRequest request);

    @Mapping(target = "username", source = "userMetadata.username")
    @Mapping(target = "roles", source = "appMetadata.roles")
    AdminCreateUserResponse toClientResponse(SupabaseAdminCreateUserResponse response);

    SupabaseAdminUpdatePasswordRequest toSupabaseRequest(AdminUpdatePasswordRequest request);

    @SuppressWarnings("unused")
    default List<IdentityProvider> mapTo(List<SupabaseIdentity> identities) {
        return identities == null ? null : identities.stream().map(
                identity -> IdentityProvider.valueOf(identity.provider().toUpperCase())
        ).toList();
    }
}
