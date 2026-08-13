package io.lexi115.sparxie.gacha.banner;

import java.util.List;

public record BannerPullResult(
        BannerType bannerType,
        List<BannerItem> items
) {
}
