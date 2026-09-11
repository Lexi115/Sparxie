package io.lexi115.sparxie.user.auth.admin.dto;

import jakarta.validation.constraints.NotBlank;

public record AdminUpdatePasswordRequest(
        @NotBlank String password
) {
}
