package io.lexi115.sparxie.inventory.inventory.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.UUID;

public record ItemRequest(
        @NotNull UUID transactionId,
        @NotNull UUID playerId,
        @NotNull Map<String, Long> items
) {
}
