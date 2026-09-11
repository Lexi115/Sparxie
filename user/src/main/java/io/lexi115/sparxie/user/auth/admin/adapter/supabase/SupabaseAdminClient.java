package io.lexi115.sparxie.user.auth.admin.adapter.supabase;

import io.lexi115.sparxie.user.auth.adapter.supabase.error.SupabaseErrorDecoder;
import io.lexi115.sparxie.user.auth.admin.adapter.supabase.dto.SupabaseAdminCreateUserRequest;
import io.lexi115.sparxie.user.auth.admin.adapter.supabase.dto.SupabaseAdminCreateUserResponse;
import io.lexi115.sparxie.user.auth.admin.adapter.supabase.dto.SupabaseAdminUpdatePasswordRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Component
@FeignClient(
        name = "user",
        contextId = "supabaseAdminClient",
        url = "${app.http.client-uri.supabase}",
        configuration = {
                SupabaseAdminClientConfig.class,
                SupabaseErrorDecoder.class
        }
)
public interface SupabaseAdminClient {
    @PostMapping("/admin/users")
    SupabaseAdminCreateUserResponse createUser(@Valid @RequestBody SupabaseAdminCreateUserRequest request);

    @PutMapping("/admin/users/{userId}")
    void updatePassword(
            @Valid @RequestBody SupabaseAdminUpdatePasswordRequest request,
            @PathVariable UUID userId
    );

    @DeleteMapping("/admin/users/{userId}")
    void deleteUser(@PathVariable UUID userId);
}
