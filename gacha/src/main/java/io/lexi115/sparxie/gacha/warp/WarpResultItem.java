package io.lexi115.sparxie.gacha.warp;

import io.lexi115.sparxie.gacha.banners.StarRarity;

public record WarpResultItem(
        String itemId,
        StarRarity rarity,
        WarpOutcome outcome
) {
}
