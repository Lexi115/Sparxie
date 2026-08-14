package io.lexi115.sparxie.game.warp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UUID;

public record WarpRequest(
        @NotBlank @UUID String transactionId,
        @NotBlank String bannerId,
        @NotBlank @UUID String playerId,
        @Min(1) Integer amount
) {
    public WarpRequest {
        if (amount == null) {
            amount = 1;
        }
    }
}
