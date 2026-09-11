package io.lexi115.sparxie.user.auth.adapter.supabase.admin.dto;

import jakarta.validation.constraints.NotBlank;

public record SupabaseAdminUpdatePasswordRequest(
        @NotBlank String password
) {
}
