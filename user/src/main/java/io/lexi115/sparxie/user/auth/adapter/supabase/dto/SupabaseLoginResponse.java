package io.lexi115.sparxie.user.auth.adapter.supabase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.UUID;

public record SupabaseLoginResponse(
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("refresh_token") String refreshToken,
        User user
) {
    public record User(
            UUID id,
            String email,
            @JsonProperty("created_at") Instant createdAt
    ) {
    }
}
