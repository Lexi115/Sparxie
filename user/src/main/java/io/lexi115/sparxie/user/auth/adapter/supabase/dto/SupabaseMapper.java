package io.lexi115.sparxie.user.auth.adapter.supabase.dto;

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
}
