package io.lexi115.sparxie.user.auth.adapter.supabase.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.lexi115.sparxie.user.auth.adapter.supabase.dto.SupabaseAppMetadata;
import io.lexi115.sparxie.user.auth.adapter.supabase.dto.SupabaseUserMetadata;

public record SupabaseAdminCreateUserRequest(
        String email,
        String password,
        @JsonProperty("email_confirm") boolean isEmailConfirmed,
        @JsonProperty("user_metadata") SupabaseUserMetadata userMetadata,
        @JsonProperty("app_metadata") SupabaseAppMetadata appMetadata
) {
}
