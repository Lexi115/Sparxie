package io.lexi115.sparxie.user.auth.adapter.supabase.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SupabaseErrorResponse(
        @JsonProperty("code") Integer statusCode,
        @JsonProperty("error_code") String errorCode,
        @JsonProperty("error") String error,
        @JsonProperty("error_description") String errorDescription,
        @JsonProperty("msg") String message
) {
}
