package io.lexi115.sparxie.inventory.inventories.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.UUID;

public record ItemRequest(
        @NotNull UUID transactionId,
        @NotNull Map<String, Long> items
) {
}
