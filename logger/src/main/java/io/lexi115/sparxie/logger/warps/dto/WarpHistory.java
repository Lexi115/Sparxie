package io.lexi115.sparxie.logger.warps.dto;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record WarpHistory(
        UUID playerId,
        String bannerType,
        List<WarpHistoryEntry> entries
) {
}
