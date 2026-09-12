package io.lexi115.sparxie.user.auth.admin.adapter.supabase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.lexi115.sparxie.user.auth.adapter.supabase.dto.SupabaseAppMetadata;
import io.lexi115.sparxie.user.auth.adapter.supabase.dto.SupabaseIdentity;
import io.lexi115.sparxie.user.auth.adapter.supabase.dto.SupabaseUserMetadata;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record SupabaseAdminViewUserResponse(
        @JsonProperty("id") UUID userId,
        String email,
        @JsonProperty("app_metadata") SupabaseAppMetadata appMetadata,
        @JsonProperty("user_metadata") SupabaseUserMetadata userMetadata,
        List<SupabaseIdentity> identities,
        @JsonProperty("created_at") Instant createdAt
) {
}
