package io.lexi115.sparxie.user.auth.adapters.supabase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record SupabaseRegisterResponse(
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("refresh_token") String refreshToken,
        User user
) {
    public record User(
            UUID id,
            String email,
            @JsonProperty("created_at") Instant createdAt,
            List<SupabaseIdentity> identities
    ) {
    }
}
