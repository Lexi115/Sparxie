package io.lexi115.sparxie.gacha.warp;

import io.lexi115.sparxie.gacha.banners.BannerType;

import java.util.List;

public record WarpResult(
        BannerType bannerType,
        List<WarpResultItem> items
) {
}
