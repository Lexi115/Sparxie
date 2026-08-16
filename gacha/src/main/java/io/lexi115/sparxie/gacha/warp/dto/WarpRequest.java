package io.lexi115.sparxie.gacha.warp.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UUID;

public record WarpRequest(
        @NotBlank @UUID String transactionId,
        @NotBlank String bannerId,
        @NotBlank @UUID String playerId,
        @Range(min = 1, max = 10) Integer amount
) {
    public WarpRequest {
        if (amount == null) {
            amount = 1;
        }
    }
}
