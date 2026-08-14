package io.lexi115.sparxie.gacha.warp.dto;

import java.util.List;

public record WarpResultDto(
        String bannerType,
        List<WarpResultItemDto> items
) {
}
