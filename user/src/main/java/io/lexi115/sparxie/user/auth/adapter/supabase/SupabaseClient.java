package io.lexi115.sparxie.user.auth.adapter.supabase;

import io.lexi115.sparxie.user.auth.adapter.supabase.admin.dto.SupabaseAdminCreateUserRequest;
import io.lexi115.sparxie.user.auth.adapter.supabase.admin.dto.SupabaseAdminCreateUserResponse;
import io.lexi115.sparxie.user.auth.adapter.supabase.admin.dto.SupabaseAdminUpdatePasswordRequest;
import io.lexi115.sparxie.user.auth.adapter.supabase.dto.*;
import io.lexi115.sparxie.user.auth.adapter.supabase.error.SupabaseErrorDecoder;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@Service
@FeignClient(
        name = "user",
        contextId = "supabaseClient",
        url = "${app.http.client-uri.supabase}",
        configuration = SupabaseErrorDecoder.class
)
public interface SupabaseClient {
    @PostMapping("/signup")
    SupabaseRegisterResponse register(@Valid @RequestBody SupabaseRegisterRequest request);

    @PostMapping("/token?grant_type=password")
    SupabaseLoginResponse login(@Valid @RequestBody SupabaseLoginRequest request);

    @PostMapping("/token?grant_type=refresh_token")
    SupabaseRefreshTokenResponse refreshToken(@Valid @RequestBody SupabaseRefreshTokenRequest request);

    @PutMapping("/user")
    void updatePassword(
            @Valid @RequestBody SupabaseUpdatePasswordRequest request,
            @RequestHeader("Authorization") String bearerToken
    );

    @PostMapping("/admin/users")
    SupabaseAdminCreateUserResponse adminCreateUser(
            @Valid @RequestBody SupabaseAdminCreateUserRequest request,
            @RequestHeader("Authorization") String bearerToken
    );

    @PutMapping("/admin/users/{userId}")
    void adminUpdatePassword(
            @Valid @RequestBody SupabaseAdminUpdatePasswordRequest request,
            @PathVariable UUID userId,
            @RequestHeader("Authorization") String bearerToken
    );

    @DeleteMapping("/admin/users/{userId}")
    void adminDelete(@PathVariable UUID userId, @RequestHeader("Authorization") String bearerToken);

    @GetMapping("/callback")
    feign.Response callback(@SpringQueryMap Map<String, String> params);
}
