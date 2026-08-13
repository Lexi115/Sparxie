package io.lexi115.sparxie.gacha.banner;

import io.lexi115.sparxie.gacha.player.PlayerPity;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public class Banner implements Cloneable {
    private String id;
    private String name;
    private BannerType type;
    private BannerCurrency currency;
    private Map<Integer, Integer> costs;

    private Map<StarRarity, List<BannerItem>> winPool;
    private Map<StarRarity, List<BannerItem>> lossPool;
    private Map<StarRarity, NavigableMap<Integer, Double>> rarityRates;
    private Map<StarRarity, Double> winRates;

    public BannerItem pull(PlayerPity playerPity) {
        var fourStarPity = playerPity.getPity(type, StarRarity.FOUR);
        var fiveStarPity = playerPity.getPity(type, StarRarity.FIVE);
        var fourStarRate = getRarityRates(StarRarity.FOUR, fourStarPity);
        var fiveStarRate = getRarityRates(StarRarity.FIVE, fiveStarPity);
        var randomizer = ThreadLocalRandom.current();
        var roll = randomizer.nextDouble();

        // 5-star
        if (roll < fiveStarRate) {
            var outcome = decideOutcome(StarRarity.FIVE, playerPity.isGuaranteed(type, StarRarity.FIVE));
            var item = chooseItem(StarRarity.FIVE, outcome);
            return new BannerPullResult(type, item, outcome, fiveStarPity + 1, fiveStarPity + 1);
        }

        // 4-star
        if (roll < fourStarRate + fiveStarRate) {
            var outcome = decideOutcome(StarRarity.FOUR, playerPity.isGuaranteed(type, StarRarity.FOUR));
            var item = chooseItem(StarRarity.FOUR, outcome);
            return new BannerPullResult(type, item, outcome, fourStarPity + 1, fiveStarPity + 1);
        }

        // 3-star
        var outcome = decideOutcome(StarRarity.THREE, false);
        var item = chooseItem(StarRarity.THREE, outcome);
        return new BannerPullResult(type, item, outcome, 1, fiveStarPity + 1);
    }

    private BannerItem chooseItem(StarRarity rarity, BannerPullOutcome outcome) {
        var randomizer = ThreadLocalRandom.current();
        var chosenList = (rarity == StarRarity.THREE) ? lossPool.get(rarity)
                : (outcome != BannerPullOutcome.LOSS) ? winPool.get(rarity) : lossPool.get(rarity);
        return chosenList.get(randomizer.nextInt(chosenList.size()));
    }

    private BannerPullOutcome decideOutcome(StarRarity rarity, boolean guaranteed) {
        var randomizer = ThreadLocalRandom.current();
        return rarity == StarRarity.THREE ? BannerPullOutcome.LOSS
                : guaranteed ? BannerPullOutcome.GUARANTEED
                : (randomizer.nextDouble() < winRates.get(rarity)) ? BannerPullOutcome.WIN
                : BannerPullOutcome.LOSS;
    }

    private Double getRarityRates(StarRarity rarity, Integer pityValue) {
        var thresholdsMap = rarityRates.get(rarity);
        if (thresholdsMap == null) {
            return 0.0;
        }
        var probability = thresholdsMap.floorEntry(pityValue);
        return probability == null ? 0.0 : probability.getValue();
    }

    public int getCost(int pullAmount) {
        if (pullAmount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        return this.costs.getOrDefault(pullAmount, costs.getOrDefault(1, 0) * pullAmount);
    }

    @Override
    public Banner clone() {
        try {
            Banner clone = (Banner) super.clone();

            if (this.costs != null) {
                clone.setCosts(new HashMap<>(this.costs));
            }

            if (this.winPool != null) {
                var clonedWinPool = new HashMap<>(this.winPool);
                clonedWinPool.replaceAll((_, list) -> new ArrayList<>(list));
                clone.setWinPool(clonedWinPool);
            }

            if (this.lossPool != null) {
                var clonedLossPool = new HashMap<>(this.lossPool);
                clonedLossPool.replaceAll((_, list) -> new ArrayList<>(list));
                clone.setLossPool(clonedLossPool);
            }

            if (this.rarityRates != null) {
                var clonedRarityRates = new HashMap<>(this.rarityRates);
                clonedRarityRates.replaceAll((_, map) -> new TreeMap<>(map));
                clone.setRarityRates(clonedRarityRates);
            }

            if (this.winRates != null) {
                clone.setWinRates(new HashMap<>(this.winRates));
            }

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
