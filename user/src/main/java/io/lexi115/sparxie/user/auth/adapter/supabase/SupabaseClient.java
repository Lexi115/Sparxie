package io.lexi115.sparxie.user.auth.adapter.supabase;

import io.lexi115.sparxie.user.auth.adapter.supabase.dto.*;
import io.lexi115.sparxie.user.auth.adapter.supabase.error.SupabaseErrorDecoder;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/admin/users/{userId}")
    void adminUpdatePassword(
            @Valid @RequestBody SupabaseUpdatePasswordRequest request,
            @PathVariable UUID userId,
            @RequestHeader("Authorization") String bearerToken
    );

    @DeleteMapping("/admin/users/{userId}")
    void delete(@PathVariable UUID userId, @RequestHeader("Authorization") String bearerToken);
}
