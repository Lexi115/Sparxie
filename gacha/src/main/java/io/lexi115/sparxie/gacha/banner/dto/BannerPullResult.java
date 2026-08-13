package io.lexi115.sparxie.gacha.banner.dto;

import io.lexi115.sparxie.gacha.banner.BannerType;
import io.lexi115.sparxie.gacha.banner.PulledBannerItem;

import java.util.List;

public record BannerPullResult(
        BannerType bannerType,
        List<PulledBannerItem> items
) {
}
