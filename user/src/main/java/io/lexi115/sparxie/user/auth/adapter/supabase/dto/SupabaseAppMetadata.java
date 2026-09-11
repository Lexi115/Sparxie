package io.lexi115.sparxie.user.auth.adapter.supabase.dto;

import io.lexi115.sparxie.user.auth.UserRole;

import java.util.List;

public record SupabaseAppMetadata(
        List<UserRole> roles
) {
}
