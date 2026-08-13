package io.lexi115.sparxie.game.banner;

import java.util.List;

public record BannerPullResultDto(
        String bannerType,
        List<PulledBannerItemDto> items
) {
}
