package io.lexi115.sparxie.gacha.warps.dto;

import java.util.List;

public record WarpResponse(
        List<WarpResultItemDto> items
) {
}
