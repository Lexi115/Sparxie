package io.lexi115.sparxie.user.auth.adapter.supabase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record SupabaseRefreshTokenRequest(
        @JsonProperty("refresh_token") @NotBlank String refreshToken
) {
}
