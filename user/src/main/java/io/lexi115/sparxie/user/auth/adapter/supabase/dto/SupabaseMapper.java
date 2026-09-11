package io.lexi115.sparxie.user.auth.adapter.supabase.dto;

import io.lexi115.sparxie.user.auth.admin.adapter.supabase.dto.SupabaseAdminCreateUserRequest;
import io.lexi115.sparxie.user.auth.admin.adapter.supabase.dto.SupabaseAdminCreateUserResponse;
import io.lexi115.sparxie.user.auth.admin.adapter.supabase.dto.SupabaseAdminUpdatePasswordRequest;
import io.lexi115.sparxie.user.auth.admin.dto.AdminCreateUserRequest;
import io.lexi115.sparxie.user.auth.admin.dto.AdminCreateUserResponse;
import io.lexi115.sparxie.user.auth.admin.dto.AdminUpdatePasswordRequest;
import io.lexi115.sparxie.user.auth.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupabaseMapper {
    SupabaseRegisterRequest toSupabaseRequest(RegisterRequest request);

    @Mapping(target = "userId", source = "response.user.id")
    @Mapping(target = "email", source = "response.user.email")
    @Mapping(target = "createdAt", source = "response.user.createdAt")
    RegisterResponse toClientResponse(SupabaseRegisterResponse response, String username);

    SupabaseLoginRequest toSupabaseRequest(LoginRequest request);

    @Mapping(target = "userId", source = "response.user.id")
    @Mapping(target = "email", source = "response.user.email")
    @Mapping(target = "createdAt", source = "response.user.createdAt")
    LoginResponse toClientResponse(SupabaseLoginResponse response);

    SupabaseRefreshTokenRequest toSupabaseRequest(RefreshTokenRequest request);

    RefreshTokenResponse toClientResponse(SupabaseRefreshTokenResponse response);

    SupabaseUpdatePasswordRequest toSupabaseRequest(UpdatePasswordRequest request);

    @Mapping(target = "userMetadata.username", source = "username")
    @Mapping(target = "userMetadata.email", source = "email")
    @Mapping(target = "appMetadata.roles", source = "roles")
    SupabaseAdminCreateUserRequest toSupabaseRequest(AdminCreateUserRequest request);

    @Mapping(target = "username", source = "userMetadata.username")
    @Mapping(target = "roles", source = "appMetadata.roles")
    AdminCreateUserResponse toClientResponse(SupabaseAdminCreateUserResponse response);

    SupabaseAdminUpdatePasswordRequest toSupabaseRequest(AdminUpdatePasswordRequest request);
}
