package io.lexi115.sparxie.gacha.player.pity;

import io.lexi115.sparxie.gacha.banner.BannerType;
import io.lexi115.sparxie.gacha.banner.StarRarity;
import io.lexi115.sparxie.gacha.warp.WarpOutcome;
import io.lexi115.sparxie.gacha.warp.WarpResultItem;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Pity {
    private List<PityTracker> trackers = new ArrayList<>();

    private PityTracker getTracker(BannerType bannerType, StarRarity rarity) {
        return trackers.stream()
                .filter(t -> t.getBannerType() == bannerType && t.getRarity() == rarity)
                .findFirst()
                .orElseGet(() -> {
                    var newTracker = new PityTracker(bannerType, rarity, 0, false);
                    trackers.add(newTracker);
                    return newTracker;
                });
    }

    public void increment(BannerType bannerType, StarRarity rarity) {
        var tracker = getTracker(bannerType, rarity);
        tracker.setCounter(tracker.getCounter() + 1);
    }

    public void reset(BannerType bannerType, StarRarity rarity) {
        var tracker = getTracker(bannerType, rarity);
        tracker.setCounter(0);
    }

    public boolean isGuaranteed(BannerType bannerType, StarRarity rarity) {
        var tracker = getTracker(bannerType, rarity);
        return tracker.isGuaranteed();
    }

    public void setGuaranteed(BannerType bannerType, StarRarity rarity, boolean guaranteed) {
        var tracker = getTracker(bannerType, rarity);
        tracker.setGuaranteed(guaranteed);
    }

    public int getValue(BannerType bannerType, StarRarity rarity) {
        return getTracker(bannerType, rarity).getCounter();
    }

    public void update(final BannerType bannerType, final WarpResultItem warpResultItem) {
        var pulledRarity = warpResultItem.rarity();
        var resultOutcome = warpResultItem.outcome();
        increment(bannerType, StarRarity.FOUR);
        increment(bannerType, StarRarity.FIVE);
        if (pulledRarity != StarRarity.THREE) {
            setGuaranteed(bannerType, pulledRarity, resultOutcome == WarpOutcome.LOSS);
            reset(bannerType, pulledRarity);
        }
    }
}
