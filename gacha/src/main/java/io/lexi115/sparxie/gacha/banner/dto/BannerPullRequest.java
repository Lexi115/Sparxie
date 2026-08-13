package io.lexi115.sparxie.gacha.banner.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BannerPullRequest(
        @NotNull @Min(0) Long transactionId,
        @NotBlank String bannerId,
        @NotBlank String playerId,
        @NotNull @Min(1) Integer amount
) {
}
