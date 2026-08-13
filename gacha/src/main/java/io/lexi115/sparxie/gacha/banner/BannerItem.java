package io.lexi115.sparxie.gacha.banner;

public record BannerItem(
        Long id,
        StarRarity rarity,
        BannerPullOutcome outcome
) {
}
