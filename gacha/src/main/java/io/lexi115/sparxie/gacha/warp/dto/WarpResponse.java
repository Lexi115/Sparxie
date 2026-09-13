package io.lexi115.sparxie.gacha.warp.dto;

import java.util.List;

public record WarpResponse(
        List<WarpResultItemDto> items
) {
}
