package io.lexi115.sparxie.user.auth.adapters.supabase.dto;

import io.lexi115.sparxie.user.auth.UserRole;

import java.util.List;

public record SupabaseAppMetadata(
        List<UserRole> roles
) {
}
