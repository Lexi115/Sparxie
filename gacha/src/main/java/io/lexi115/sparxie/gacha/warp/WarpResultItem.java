package io.lexi115.sparxie.gacha.warp;

import io.lexi115.sparxie.gacha.banner.BannerItem;

public record WarpResultItem(
        BannerItem item,
        WarpOutcome outcome
) {
}
