package io.lexi115.sparxie.gacha.player;

import io.lexi115.sparxie.gacha.banner.BannerPullOutcome;
import io.lexi115.sparxie.gacha.banner.BannerType;
import io.lexi115.sparxie.gacha.banner.PulledBannerItem;
import io.lexi115.sparxie.gacha.banner.StarRarity;

import java.util.HashMap;
import java.util.Map;

public class PlayerPity {
    private final Map<BannerType, Map<StarRarity, Integer>> pities = new HashMap<>();
    private final Map<BannerType, Map<StarRarity, Boolean>> guarantees = new HashMap<>();

    public void setPity(BannerType bannerType, StarRarity rarity, int value) {
        if (rarity == null) {
            throw new IllegalArgumentException("Rarity cannot be null");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Pity value cannot be less than 0");
        }
        pities
                .computeIfAbsent(bannerType, _ -> new HashMap<>())
                .put(rarity, value);
    }

    public int getPity(BannerType bannerType, StarRarity rarity) {
        if (rarity == null) {
            throw new IllegalArgumentException("Rarity cannot be null");
        }
        return pities
                .computeIfAbsent(bannerType, _ -> new HashMap<>())
                .getOrDefault(rarity, 0);
    }

    public void incrementPity(BannerType bannerType, StarRarity rarity) {
        var bannerTypePity = pities.computeIfAbsent(bannerType, _ -> new HashMap<>());
        bannerTypePity.put(rarity, bannerTypePity.getOrDefault(rarity, 0) + 1);
    }

    public void resetPity(BannerType bannerType, StarRarity rarity) {
        pities
                .computeIfAbsent(bannerType, _ -> new HashMap<>())
                .put(rarity, 0);
    }

    public boolean isGuaranteed(BannerType bannerType, StarRarity rarity) {
        return guarantees
                .computeIfAbsent(bannerType, _ -> new HashMap<>())
                .getOrDefault(rarity, false);
    }

    public void setGuaranteed(BannerType bannerType, StarRarity rarity, boolean value) {
        if (rarity == null) {
            throw new IllegalArgumentException("Rarity cannot be null");
        }
        guarantees
                .computeIfAbsent(bannerType, _ -> new HashMap<>())
                .put(rarity, value);
    }

    public void updatePity(final BannerType bannerType, final PulledBannerItem pulledBannerItem) {
        var pulledRarity = pulledBannerItem.item().rarity();
        var resultOutcome = pulledBannerItem.outcome();
        incrementPity(bannerType, StarRarity.FOUR);
        incrementPity(bannerType, StarRarity.FIVE);
        if (pulledRarity != StarRarity.THREE) {
            setGuaranteed(bannerType, pulledRarity, resultOutcome == BannerPullOutcome.LOSS);
            resetPity(bannerType, pulledRarity);
        }
    }
}
