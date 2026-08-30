package io.lexi115.sparxie.user.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
        @NotBlank String username,
        @NotBlank String password
) {
}
