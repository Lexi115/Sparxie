package io.lexi115.sparxie.game.histories.dto;

import lombok.Builder;

@Builder
public record WarpHistoryEntryItem(
        String itemId,
        String outcome
) {
}
