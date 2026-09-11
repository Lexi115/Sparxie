package io.lexi115.sparxie.user.auth.adapter.supabase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SupabaseUserMetadata(
        String email,
        @JsonProperty("name") String name,
        @JsonProperty("full_name") String fullName,
        @JsonProperty("username") String username
) {
}
