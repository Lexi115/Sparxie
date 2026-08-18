package io.lexi115.sparxie.gacha.warp.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

public record WarpRequest(
        @NotBlank UUID transactionId,
        @NotBlank String bannerId,
        @NotBlank UUID playerId,
        @Range(min = 1, max = 10) Integer amount
) {
    public WarpRequest {
        if (amount == null) {
            amount = 1;
        }
    }
}
