package io.lexi115.sparxie.user.auth.adapter.supabase.dto;

import jakarta.validation.constraints.NotBlank;

public record SupabaseUpdatePasswordRequest(
        @NotBlank String password
) {
}
