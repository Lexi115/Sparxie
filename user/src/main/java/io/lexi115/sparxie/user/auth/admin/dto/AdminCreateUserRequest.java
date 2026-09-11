package io.lexi115.sparxie.user.auth.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.lexi115.sparxie.user.auth.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AdminCreateUserRequest(
        @NotBlank String username,
        @NotBlank @Email String email,
        @NotNull @JsonProperty("isEmailConfirmed") Boolean isEmailConfirmed,
        @NotBlank String password,
        @NotEmpty List<UserRole> roles
) {
}
