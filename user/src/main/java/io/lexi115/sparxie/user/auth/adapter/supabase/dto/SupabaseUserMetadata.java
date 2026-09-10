package io.lexi115.sparxie.user.auth.adapter.supabase.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SupabaseUserMetadata(
        String email,
        @JsonProperty("full_name") String username
) {
}
