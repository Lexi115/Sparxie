package io.lexi115.sparxie.logger.warps.dto;

import lombok.Builder;

@Builder
public record WarpHistoryEntryItem(
        String itemId,
        String outcome
) {
}
