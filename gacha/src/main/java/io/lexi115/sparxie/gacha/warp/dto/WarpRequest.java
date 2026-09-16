package io.lexi115.sparxie.gacha.warp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

public record WarpRequest(
        @NotNull UUID transactionId,
        @NotBlank String bannerId,
        @Range(min = 1, max = 10) Integer amount
) {
    public WarpRequest {
        if (amount == null) {
            amount = 1;
        }
    }
}
