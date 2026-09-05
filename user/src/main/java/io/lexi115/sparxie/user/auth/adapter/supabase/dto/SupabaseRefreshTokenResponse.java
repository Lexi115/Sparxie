package io.lexi115.sparxie.user.auth.adapter.supabase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SupabaseRefreshTokenResponse(
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("refresh_token") String refreshToken
) {
}
