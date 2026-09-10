package io.lexi115.sparxie.user.auth.adapter.supabase.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SupabaseRegisterRequest(
        @NotBlank @Email String email,
        @NotBlank String password
) {
}
