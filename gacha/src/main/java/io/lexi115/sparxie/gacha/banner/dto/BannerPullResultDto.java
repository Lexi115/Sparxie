package io.lexi115.sparxie.gacha.banner.dto;

import java.util.List;

public record BannerPullResultDto(
        String bannerType,
        List<PulledBannerItemDto> items
) {
}
