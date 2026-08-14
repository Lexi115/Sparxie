package io.lexi115.sparxie.gacha.banner.dto;

import io.lexi115.sparxie.gacha.banner.BannerItem;
import io.lexi115.sparxie.gacha.banner.BannerPullOutcome;

public record PulledBannerItem(
        BannerItem item,
        BannerPullOutcome outcome
) {
}
