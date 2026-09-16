package io.lexi115.sparxie.user.auth.admin.adapter.supabase.dto;

import jakarta.validation.constraints.NotBlank;

public record SupabaseAdminUpdatePasswordRequest(
        @NotBlank String password
) {
}
