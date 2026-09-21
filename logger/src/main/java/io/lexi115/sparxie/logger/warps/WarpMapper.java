package io.lexi115.sparxie.logger.warps;

import io.lexi115.sparxie.logger.warps.dto.*;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface WarpMapper {
    default WarpHistory toHistory(List<WarpPerformed> original, UUID playerId, String bannerType) {
        return WarpHistory.builder()
                .playerId(playerId)
                .bannerType(bannerType)
                .entries(original.stream().map(this::toHistoryEntry).toList())
                .build();
    }

    WarpHistoryEntry toHistoryEntry(WarpPerformed original);

    default List<WarpHistoryEntryItem> toHistoryEntryItem(WarpResponse original) {
        return original.items().stream().map(this::toHistoryEntryItem).toList();
    }

    WarpHistoryEntryItem toHistoryEntryItem(WarpItem original);
}
