package io.lexi115.sparxie.game.warps.dto;

import java.util.List;

public record GachaWarpResponse(
        String bannerType,
        List<GachaWarpItem> items
) {
}
