package io.lexi115.sparxie.game.warp.dto;

import java.util.List;

public record WarpResultDto(
        String bannerType,
        List<WarpResultItemDto> items
) {
}
