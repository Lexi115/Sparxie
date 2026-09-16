package io.lexi115.sparxie.gacha.players;

import io.lexi115.sparxie.gacha.banners.BannerType;
import io.lexi115.sparxie.gacha.banners.StarRarity;
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
