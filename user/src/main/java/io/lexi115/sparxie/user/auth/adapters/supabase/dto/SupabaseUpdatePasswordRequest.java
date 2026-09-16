package io.lexi115.sparxie.user.auth.adapters.supabase.dto;

import jakarta.validation.constraints.NotBlank;

public record SupabaseUpdatePasswordRequest(
        @NotBlank String password
) {
}
