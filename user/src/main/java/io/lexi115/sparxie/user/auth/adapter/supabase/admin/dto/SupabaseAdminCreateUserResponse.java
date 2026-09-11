package io.lexi115.sparxie.user.auth.adapter.supabase.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.lexi115.sparxie.user.auth.adapter.supabase.dto.SupabaseAppMetadata;
import io.lexi115.sparxie.user.auth.adapter.supabase.dto.SupabaseUserMetadata;

import java.time.Instant;
import java.util.UUID;

public record SupabaseAdminCreateUserResponse(
        @JsonProperty("id") UUID userId,
        String email,
        @JsonProperty("app_metadata") SupabaseAppMetadata appMetadata,
        @JsonProperty("user_metadata") SupabaseUserMetadata userMetadata,
        @JsonProperty("created_at") Instant createdAt
) {
}
