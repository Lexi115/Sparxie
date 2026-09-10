package io.lexi115.sparxie.gacha.player.pity;

import io.lexi115.sparxie.gacha.banner.BannerType;
import io.lexi115.sparxie.gacha.banner.StarRarity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PityTracker {
    private BannerType bannerType;
    private StarRarity rarity;
    private int counter;
    private boolean guaranteed;
}
